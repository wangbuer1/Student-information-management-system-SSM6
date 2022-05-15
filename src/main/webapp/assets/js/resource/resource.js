$(function () {
    //初始化表格树
    if ($("#resourceTableTree").length) {
        layui.tableTree({elem: "#resourceTableTree"})
    }
    //表单
    var form = layui.form;
    //监听资源状态
    form.on('switch(resourceState)', function (data) {
        if (this.checked) {
            this.value = 1;
        } else {
            this.value = 0;
        }
    });
    //icon图标
    var className;
    $('.icon li').on('click', function () {
        className = $(this).find('span').get(0).className;
        $(this).css('background-color', '#009FD9').css('color', '#fff');
        $(this).siblings().css('background-color', '#F8F8F8').css('color', '#000');
    });
    //初始化树
    initResourceTree();
})
function initResourceTree() {
    sys.ajax.doAjax({
        type: 'get',
        url: ctx + "resource/tree",
        dataType: 'json',
        success: function (result) {
            if (result.code == 0) {
                zNodes = result.data;
                $.fn.zTree.init($("#resourceTree"), setting, zNodes);
            } else {
                sysLayer.util.alert(result.msg);
            }
        }
    });
}
/**
 * 校验表单【addResource】
 * @returns {*|jQuery}
 */
function validateAddResourceForm() {
    return $('#addResource form').validate({
        rules: {
            name: {
                required: true,
                maxlength: 10
            },
            sort: {
                required: true
            }
        },
        messages: {
            name: {
                required: "请输入资源名称",
                maxlength: "资源名称最多10个字符"
            },
            sort: {
                required: "请输入排序值"
            }
        }
    })
}
/**
 * 弹出新增资源框
 */
function addResourcePopBox() {
    $("#addResource").removeClass("layui-hide");
  //  validateAddResourceForm();
    var index = layer.open({
        title: "新增资源",
        area: ['800px', '550px'],
        btn: ['保存', '取消'],
        btnAlign: 'c',
        type: 1,
        /*  skin:"layui-layer-molv",*/
        /*skin:"layui-layer-lan",*/
        content: $('#addResource'),//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        yes: function () {
          /*  if (!validateAddResourceForm().form()) {
                return;
            }*/
            sys.ajax.doAjax({
                type: 'post',
                url: ctx + "resource/add",
                data: $("#addResource form").serialize2(),
                dataType: 'json',
                success: function (result) {
                    if (result.code == 0) {
                        layer.close(index);
                        window.location.reload();
                    } else {
                        sysLayer.util.alert(result.msg);
                    }
                }
            });
        },
        end: function () {
            $("#addResource form")[0].reset();
            $("#addResource").addClass("layui-hide");
            layer.close(index);
        }
    });
}

/**********ztree相关 开始********/
var setting = {
    view: {
        dblClickExpand: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeClick: beforeClick,
        onClick: onClick
    }
};
var zNodes = [];
function beforeClick(treeId, treeNode) {
    return true;
}
function onClick(e, treeId, treeNode) {
    $("#pid").val(treeNode.name);
    $("input[name='pid']").val(treeNode.id);
}

function showMenu() {
    $("#resourceTreeContent").slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#resourceTreeContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" ||  event.target.id == "pid" ||event.target.id == "resourceTreeContent" || $(event.target).parents("#resourceTreeContent").length > 0)) {
        hideMenu();
    }
}
/**********ztree相关 结束********/

/**
 * 设置图标
 */
function setIcon() {
    $("#setIcon").removeClass("layui-hide");
    var index = layer.open({
        title: "设置图标",
        area: ['800px', '550px'],
        btn: ['保存', '取消'],
        btnAlign: 'c',
        type: 1,
        content: $('#setIcon'),//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        yes: function () {
            $("input[name='icon']").val(className);
            layer.close(index);
        },
        end: function () {
            $("#setIcon").addClass("layui-hide");
            layer.close(index);
        }
    });
}