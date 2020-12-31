import com.sct.sms.annotation.IdEnum;
import com.sct.sms.properties.SendProperties;
import com.sct.sms.service.SendResultType;

import java.lang.annotation.Annotation;

public class TestMain {
    public static void main(String[] args) {
        SendResultType sendResultType = SendResultType.getType(1);
        IdEnum.getType(SendResultType.class);
        System.out.println(sendResultType.getId());
        System.out.println(sendResultType.name());
        System.out.println(sendResultType);
//        DescriptionUtils.scan(SendResultType.class);
    }

    private static void printAnnotation(String msg, Annotation... annotations) {
        System.out.println("==============" + msg + "======================");
        if (annotations == null) {
            System.out.println("Annotation is null");
        }
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        System.out.println();
    }
}
