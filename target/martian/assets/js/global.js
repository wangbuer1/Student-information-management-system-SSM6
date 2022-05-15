/**
 * 表单序列化提交(优化radio,checkbox)
 * @returns {*}
 */
$.fn.serialize2 = function () {
    var a = this.serializeArray();
    var _this = this;
    var $radio = $('input[type=radio],input[type=checkbox]', _this);
    var temp = {};
    $.each($radio, function () {
        if (!temp.hasOwnProperty(this.name)) {
            if ($("input[name='" + this.name + "']:checked", _this).length == 0) {
                temp[this.name] = $(this).val();
                a.push({name: this.name, value: $(this).val()});
            }
        }
    });
    return jQuery.param(a);
};
/**
 * 表单数据转成json对象
 * @returns {{}}
 */
$.fn.serializeJson = function () {
    var a = this.serializeArray();
    var _this = this;
    var $radio = $('input[type=radio],input[type=checkbox]', _this);
    var temp = {};
    $.each($radio, function () {
        if (!temp.hasOwnProperty(this.name)) {
            if ($("input[name='" + this.name + "']:checked", _this).length == 0) {
                temp[this.name] = $(this).val();
                a.push({name: this.name, value: $(this).val()});
            }
        }
    });
    var serializeObj = {};
    $(a).each(function () {
        serializeObj[this.name] = this.value;
    });
    return serializeObj;
};

/**
 * 系统弹层
 */
window.sysLayer = {
    util: {
        showLoading: function (type) {
            type = type || 3;
            layer.load(type);
        },
        hideLoading: function () {
            layer.closeAll('loading');
        },
        alert: function (message, opts) {
            var _opts = $.extend({}, {content: message}, opts || {});
            layer.open({
                type: 0,
                title: '提示',
                content: message,
                closeBtn: 0,
                yes: function (index, layero) {
                    _opts.ok && _opts.ok();
                    layer.close(index);
                }
            });
        },
        closeAlert: function () {
            layer.closeAll('dialog');
        },
        msg: function (message, opts) {
            var _opts = $.extend({}, {}, opts || {});
            layer.msg(message, _opts);
        },
        confirm: function (message, opts) {
            var _opts = $.extend({}, {content: message}, opts || {});
            layer.confirm(message, {icon: 0, title: '提示'}, function (index) {
                if (_opts.ok && _opts.ok()) {
                    _opts.ok()
                }
                layer.close(index);
            });
        },
        openUrl: function (title, url, width, height) {
            layer.open({
                type: 2,
                content: url,
                title: title,
                area: [width, height]
            });
        },
        closeOpenedUrl: function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    }
};

/**
 * 系统对象
 */
window.sys = {
    util: {
        isEmpty: function (text) {
            if ((typeof _ != 'undefined') && _.isEmpty) {
                return _.isEmpty(text);
            }
            return !text || null == text || "" == text || "null" == text || "undefined" == text;
        }
    },
    ajax: {
        doAjax: function (opts) {
            var ajaxOptions = $.extend({}, sys.ajax.defaults, opts || {});
            sysLayer.util.showLoading();
            ajaxOptions.success = function (result) {
                sysLayer.util.hideLoading();
                if (ajaxOptions.datatype == 'json') {
                    if (typeof result == 'string') {
                        try {
                            result = $.parseJSON(result);
                        } catch (e) {
                            console.error(e)
                        }
                    }
                }
                if (typeof opts.success == 'function') {
                    opts.success(result);
                }
            };
            $.ajax(ajaxOptions);
        },
        defaults: {
            error: function (x, s, e) {
                var errorMsg = '';
                if (s == 'timeout') {
                    errorMsg = '连接超时';
                } else {
                    var errorCode = x.status;
                    if (errorCode == '401') {
                        errorMsg = '401 - 访问被拒绝';
                    } else if (errorCode == '403') {
                        errorMsg = '403 - 禁止访问';
                    } else if (errorCode == '404') {
                        errorMsg = '404 - 未找到';
                    } else if (errorCode == '500') {
                        errorMsg = '500 - 内部服务器错误';
                    } else if (errorCode == '12029') {
                        errorMsg = '无法建立HTTP连接';
                    } else {
                        errorMsg = e;
                    }
                }
                if (!sys.util.isEmpty(errorMsg)) sysLayer.util.alert(errorMsg);
                sysLayer.util.hideLoading();
            }
        }
    }
};

/**
 * 系统分页
 */
window.sysPaging = {
    opts: {
        pageId: "pageId",//渲染Id,默认pageId
        type: "GET",//请求类型：GET,POST,默认GET
        url: "",//请求URL
        data: {},//请求数据
        pageNo: 1,//页码,默认第1页
        pageSize: 10,//页大小，默认10条数据
        callback: null//分页回调，渲染数据
    },
    init: function (params) {
        if (!params.data) {
            params.data = sysPaging.opts.data;
        }
        if (!params.pageNo || params.pageNo < 1) {
            params.data.pageNo = sysPaging.opts.pageNo;
        }
        if (!params.pageSize || params.pageSize < 1) {
            params.data.pageSize = sysPaging.opts.pageSize;
        }
        if (!params.type) {
            params.type = sysPaging.opts.type;
        }
        if (!params.pageId) {
            params.pageId = sysPaging.opts.pageId;
        }
        sys.ajax.doAjax({
            type: params.type,
            url: params.url,
            data: params.data,
            dataType: "json",
            success: function (result) {
                if (result.code == 0) {
                    var laypage = layui.laypage;
                    //执行一个laypage实例
                    laypage.render({
                        elem: params.pageId,
                        count: result.data.totalCount,
                        limit: result.data.pageSize,
                        first: '首页',
                        last: '尾页',
                        layout: ['count', 'prev', 'page', 'next'],
                        theme: '#1E9FFF',
                        jump: function (obj, first) {
                            console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                            if (first) {
                                if (typeof params.callback == 'function') {
                                    params.callback(result.data);
                                }
                            } else {
                                params.data.pageNo = obj.curr;
                                params.data.pageSize = result.data.pageSize;
                                sysPaging.loadData(params)
                            }
                        }
                    })
                } else {
                    sysLayer.util.alert(result.msg);
                }
            }
        });
    },
    loadData: function (params) {
        sys.ajax.doAjax({
            type: params.type,
            url: params.url,
            data: params.data,
            dataType: 'json',
            success: function (result) {
                if (result.code == 0) {
                    if (typeof params.callback == 'function') {
                        params.callback(result.data);
                    }
                } else {
                    sysLayer.util.alert(result.msg);
                }
            }
        });
    }
};
/**
 * 提交成功跳转页面
 * @param form
 * @param successUrl
 * @param isAlert
 * @param alertStr
 */
function goSaveOrUpdate(form, successUrl, isAlert, alertStr) {
    var thisForm = $(form);
    var url = thisForm.attr('action');
    sys.ajax.doAjax({
        type: 'post',
        url: url,
        data: thisForm.serialize2(),
        dataType: 'json',
        success: function (result) {
            if (result.code == 0) {
                if (isAlert) {
                    sysLayer.util.alert(sys.util.isEmpty(alertStr) ? "保存成功" : alertStr, {
                        ok: function () {
                            window.location.href = successUrl;
                        }
                    });
                } else {
                    window.location.href = successUrl;
                }
            } else {
                sysLayer.util.alert(result.msg);
            }
        }
    });
}