var bookPage;
$(function () {
    bookPage = new BookPageObj();
    bookPage.init();

});
function BookPageObj() {
    /*基础配置*/
    this.el = 'body';
    this.addLay = null;
    this.updateLay = null;
    this.validateCong = {};
    /*end 基础配置*/
}
BookPageObj.prototype = {
    /*操作*/
    searchObj : {
        title: '',
        pageNum: 1,
        pageSize: 10
    },
    loadAreaSize:['600px', '400px'],//弹出框的宽高
    doSearch:function() {
        var sTitle = $.trim($('#title').val());
        bookPage.searchObj.title= sTitle || '';
        bookPage.searchObj.pageNum = 1;  //reset
        bookPage.renderPages(true);
    },
    doAdd:function() {
        var data = $('#addForm').serializeArray();
        ajaxCommonFun({
            type: 'POST',
            url: '/addbook.do',
            data: data,
            success: function (t) {
                layer.close(addLay);
                layer.msg(t.message);
                bookPage.renderPages(true);
            }
        });
    },
    doUpdate:function() {
        var data = $("#updateForm").serializeArray();
        ajaxCommonFun({
            type: 'POST',
            url: '/updatebook.do',
            data: data,
            success: function (t) {
                layer.close(updateLay);
                layer.msg(t.message);
                bookPage.renderPages();
            }
        });
    },
    doDelete:function () {
        var $this = $(this),
            $parent = $this.closest('tr'),
            sid = $parent.find('input[type=hidden]').val();
        var deleteLay = layer.confirm('是否确认删除？', {
            btn: ['是', '否'] //按钮
        }, function () {
            if($('#bookTemplate').find('tbody tr').length == 1 && this.searchObj.pageNum !== 1){ //当当前只有一页的时候
                this.searchObj.pageNum = this.searchObj.pageNum - 1;
            }

            ajaxCommonFun({
                type: 'POST',
                url: 'delete.do?bookid=' + sid,
                success: function (t) {
                    layer.close(deleteLay);
                    layer.msg(t.message);
                    bookPage.renderPages(true);
                }
            });
        });
    },
    /*end操作*/

    /*弹出框*/
    renderUpdatePage:function () {
        var $this = $(this),
            $parent = $this.closest('tr'),
            sid = $parent.find('input[type=hidden]').val();
        getHtmlByUrl({
            url: "/edit.html?bookid=" + sid,
            success: function (res) {
                updateLay = layer.open({
                    type: 1,
                    area: bookPage.loadAreaSize,
                    content: res
                });
            }
        })
    },
    renderAddPage:function () {
        var that = this;
        getHtmlByUrl({
            url: '/add.html',
            success: function (res) {
                addLay = layer.open({
                    type: 1,
                    area: bookPage.loadAreaSize, //宽高
                    content: res
                });
            }
        })
    },
    /*end弹出框*/

    /*页面初始化和事件绑定*/
    bindEvents:function() {
        /*update*/
        $(this.el).undelegate()
            .delegate('a[name=bookEdit]','click',bookPage.renderUpdatePage)
            .delegate('a[name=bookDelete]','click',bookPage.doDelete)
            .delegate('#btn','click',bookPage.doSearch)
            .delegate('#addbook','click',bookPage.renderAddPage)
            // .delegate('#showDiv','click',bookPage.sumit2)
            // .delegate('#saveBook','click',doAdd)
            .delegate('#updateBtn','click',bookPage.doUpdate);
    },
    renderPages:function(isFirst) {
        var url = '/list.html', isAdd = false;
        if(this.searchObj.title){
            url = url+'?title='+this.searchObj.title;
            isAdd = true;
        }
        if(this.searchObj.pageSize){
            url = isAdd ? url+'&pageSize='+this.searchObj.pageSize : url+'?pageSize='+this.searchObj.pageSize;
            isAdd = true;
        }
        if(this.searchObj.pageSize){
            url = isAdd ? url+'&page='+this.searchObj.pageNum :  url+'?page='+this.searchObj.pageNum;
        }
        getHtmlByUrl({
            url: url,
            success: function (res) {
                $("#bookTemplate").html(res);
                if(isFirst){
                    bookPage.buildPageArea();
                }
            }
        });
    },
    /*end页面初始化和事件绑定*/

    /*分页*/
    doChangePage:function(num) {
        this.searchObj.pageNum = num;
        bookPage.renderPages();
    },
    buildPageArea:function() {
        var pageAllCount = $('#pageAllCount').val(),
            totalPage = Math.ceil(pageAllCount/this.searchObj.pageSize);
        laypage({
            cont: 'page',
            pages: totalPage,
            curr: this.searchObj.pageNum,
            jump: function(obj, first){
                if(!first){
                    bookPage.doChangePage(obj.curr);
                }
            }
        });
    },
    /*end分页*/

    /*页面初始化*/
    init:function() {
        bookPage.renderPages(true);
        bookPage.bindEvents();
    }
    /*end 页面初始化*/
};