require.config({
    baseUrl: "js/lib",      //设置根路径
    paths:{             //设置各模块的别名和路径
        "jquery":["jquery.min"],
        "bootstrap":["bootstrap.min"],
        "bootstrapTable":["plugins/bootstrap-table/bootstrap-table.min"],
        "bootstrap_table_mobile":["plugins/bootstrap-table/bootstrap-table-mobile.min"],
        "bootstrap_table_zh_CN":["plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min"],
        "bootstrap-select":["bootstrap-select-select-1.12.4/dist/js/bootstrap-select.min"],
        "metisMenu":["plugins/metisMenu/jquery.metisMenu"],
        "slimscroll":["plugins/slimscroll/jquery.slimscroll.min"],
        "layer":["layer/layer","layer/mobile/layer"],
        "hplus":["hplus"],
        "contabs":["contabs"],
        "pace":["plugins/pace/pace.min"],
        "chosen":["plugins/chosen/chosen.jquery"],
        "content":["content"]
    },
    waitSecond:0,
    shim:{              //非AMD规范的模块不能直接调用，该属性用于设置非AMD规范的模块，deps用于设置该模块的依赖
        "bootstrap":{
            deps:[
                "jquery",
                "css!../../css/bootstrap.min.css",
                "css!../../css/font-awesome.min.css",
                "css!../../css/animate.css",
                "css!../../css/style.css"
            ],
            exports: "bootstrap"    //exports暴露对应的全局变量名称（设置别名）
        },
        "bootstrapTable":{
            deps:[
                "jquery",
                "bootstrap",
                "css!../../css/plugins/bootstrap-table/bootstrap-table.min.css"
            ],
            exports: "bootstrapTable"
        },
        "bootstrap_table_mobile":{
            deps:[
                "jquery",
                "bootstrap",
                "bootstrapTable"
            ],
            exports: "bootstrap_table_mobile"
        },
        "bootstrap_table_zh_CN":{
            deps:["jquery","bootstrap","bootstrapTable"],
            exports: "bootstrap_table_zh_CN"
        },
        "bootstrap_select":{
            deps:[
                "jquery",
                "bootstrap",
                "css!bootstrap-select-select-1.12.4/dist/css/bootstrap-select.min.css"
            ],
            exports:"bootstrap_select"
        },
        "metisMenu":{
            deps:["jquery"],
            exports: "metisMenu"
        },
        "slimscroll":{
            deps:["jquery"],
            exports: "slimscroll"
        },
        "layer":{
            deps:[
                "jquery",
                "css!/js/lib/layer/theme/default/layer.css"
                // "css!/js/lib/layer/mobile/need/layer.css"
            ],
            exports: "layer"
        },
        "hplus":{
            deps:["jquery","bootstrap","slimscroll","metisMenu"],
            exports: "hplus"
        },
        "contabs":{
            deps:["jquery"],
            exports: "contabs"
        },
        "pace":{
            deps:["jquery"],
            exports: "pace"
        },
        "chosen":{
            deps:["jquery","css!../../css/plugins/chosen/chosen.css"],
            exports: "chosen"
        },
        "content":{
            deps:["jquery","bootstrap"],
            exports: "content"
        }
    },
    map:{
        "*":{
            "css":"css.min"
        }
    }
})

