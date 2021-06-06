//从用户管理页面获取用户data
function getRole(data, index){
    $("#userId").val(data.id);
    layui.form.render();//刷新下拉框选项
}