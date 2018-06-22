require.config({
    baseUrl: "js/lib",      //设置根路径
    paths:{             //设置各模块的别名和路径
        "jquery":["jquery.min"],
        "bootstrap":["bootstrap.min"],
        "bootstrapTable":["plugins/bootstrap-table/bootstrap-table.min"],
        "bootstrap_table_mobile":["plugins/bootstrap-table/bootstrap-table-mobile.min"],
        "bootstrap_table_zh_CN":["plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min"],
        "metisMenu":["plugins/metisMenu/jquery.metisMenu"],
        "slimscroll":["plugins/slimscroll/jquery.slimscroll.min"],
        "layer":["layer/layer.min"],
        "hplus":["hplus"],
        "contabs":["contabs"],
        "pace":["plugins/pace/pace.min"],
        "chosen":["plugins/chosen/chosen.jquery"],
        "content":["content"]
    },
    shim:{              //非AMD规范的模块不能直接调用，该属性用于设置非AMD规范的模块，deps用于设置该模块的依赖
        "bootstrap":{
            deps:["jquery"],
            exports: "bootstrap"
        },
        "bootstrapTable":{
            deps:["jquery","bootstrap"],
            exports: "bootstrapTable"
        },
        "bootstrap_table_mobile":{
            deps:["jquery","bootstrap","bootstrapTable"],
            exports: "bootstrap_table_mobile"
        },
        "bootstrap_table_zh_CN":{
            deps:["jquery","bootstrap","bootstrapTable"],
            exports: "bootstrap_table_zh_CN"
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
            deps:["jquery"],
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
            deps:["jquery"],
            exports: "chosen"
        },
        "content":{
            deps:["jquery","bootstrap"],
            exports: "content"
        }
    }
})

