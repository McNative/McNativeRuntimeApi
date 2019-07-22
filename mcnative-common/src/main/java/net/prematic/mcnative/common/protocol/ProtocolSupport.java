package net.prematic.mcnative.common.protocol;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.METHOD,ElementType.TYPE,ElementType.TYPE_PARAMETER,ElementType.PACKAGE})
@Retention(RetentionPolicy.CLASS)
public @interface ProtocolSupport {

    MinecraftProtocolVersion min() default MinecraftProtocolVersion.V1_7;

    MinecraftProtocolVersion max() default MinecraftProtocolVersion.V1_14_4;
}
