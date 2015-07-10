package act.util;

import act.app.AppByteCodeScannerBase;
import act.app.event.AppEventId;
import act.asm.Type;
import act.event.AppEventListenerBase;

import java.util.EventObject;

public class ClassInfoByteCodeScanner extends AppByteCodeScannerBase {

    private ClassInfoRepository classInfoRepository;

    @Override
    protected void onAppSet() {
        app().eventBus().bind(AppEventId.CLASS_LOADER_INITIALIZED, new AppEventListenerBase("init-class-info-repo") {
            @Override
            public void on(EventObject event) {
                classInfoRepository = app().classLoader().classInfoRepository();
            }
        });
    }

    @Override
    protected boolean shouldScan(String className) {
        return true;
    }

    @Override
    public ByteCodeVisitor byteCodeVisitor() {
        return new _ByteCodeVisitor();
    }

    @Override
    public void scanFinished(String className) {
    }

    @Override
    public void allScanFinished() {
    }

    private class _ByteCodeVisitor extends ByteCodeVisitor {
        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            super.visit(version, access, name, signature, superName, interfaces);
            String myName = Type.getObjectType(name).getClassName();
            ClassNode me = classInfoRepository.node(myName);
            me.modifiers(access);
            String superType = Type.getObjectType(superName).getClassName();
            if (!Object.class.getName().equals(superType)) {
                me.parent(superType);
            }
            if (null != interfaces) {
                for (String intf: interfaces) {
                    me.parent(intf);
                }
            }
        }
    }
}
