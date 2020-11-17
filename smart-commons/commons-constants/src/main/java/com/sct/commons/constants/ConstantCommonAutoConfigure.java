package com.sct.commons.constants;

/**
 * 在通用工具中,自动注解相关的常量配置
 */
public final class ConstantCommonAutoConfigure {

    public final static class Datasource {
        public final static String Datasource_PREFIX = "datasource";
        public final static String Datasource_jdbc_PREFIX = Datasource_PREFIX + ".jdbc";

    }

    public final static class Mybatis {
        public final static String Mybatis_PREFIX = "mybatis";
        public final static String Mybatis_factor = Mybatis_PREFIX + ".factor";
        public final static String Mybatis_factor_enable = Mybatis_factor + ".enable";
        public final static String Mybatis_factor_config = Mybatis_factor + ".config-location";
        public final static String Mybatis_factor_mapper = Mybatis_factor + ".mapper-locations";
        public final static String Mybatis_scanner = Mybatis_PREFIX + ".scanner";
        public final static String Mybatis_scanner_package = Mybatis_scanner + ".base-package";

    }

    public final static class Swagger2 {
        public final static String SWAGGER2_PREFIX = "swagger";
    }

    public final static class I18N {
        public final static String I18N_PROPERTIES = "sct.i18n.locate";
    }
}
