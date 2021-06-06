$(function () {
    selectUserDetail();
})
//更具用户的账号查询用户的详细信息回显到用户编辑页面
function selectUserDetail() {
    let username=localStorage.getItem("userName")
    let data={"username":username}
    let result=myPostTokenAjax('user/selectUserDetail',data);
    $("#username").val(result.data.username);
    $("#realName").val(result.data.realName);
    $("#sex").val(result.data.sex);
    $("#phone").val(result.data.phone);
    $("#email").val(result.data.email);
    $("#headImg").attr('src',"../../static/img/"+result.data.icon);
}

