function myPostAjax(url,data) {
    let BackUrl="http://localhost:9000/"
    let result=null;
    $.ajax({
        url: BackUrl+url,
        data: JSON.stringify(data),
        async:false,//同步=关掉异步
        // headers:{
        //     ContentType:"application/json;charset=UTF-8"
        // },
        contentType: 'application/json;charset=UTF-8',
        type: "post",
        dataType: 'json',
        xhrFields: {
            withCredentials: true //允许跨域带Cookie
        },
        //
        success: function (data) {
            result = data;
        }
    })
    return result;
}

function myPostTokenAjax(url,data) {
    let BackUrl="http://localhost:9000/"
    let tokenHead=localStorage.getItem("tokenHead");
    let token=localStorage.getItem("token");
    let result = null;
    $.ajax({
        type:"post",
        url: BackUrl+url,
        data: JSON.stringify(data),
        headers:{"Authorization":tokenHead+token},
        async: false, //同步
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        xhrFields: {
            withCredentials: true //允许跨域带Cookie
        },
        success: function(data) {
            result=data;
            if(result.code==401){
                window.location="login.html";
            }
        }
    });
    return result;
}

function myPutTokenAjax(url,data) {
    let BackUrl="http://localhost:9000/"
    let tokenHead=localStorage.getItem("tokenHead");
    let token=localStorage.getItem("token");
    let result = null;
    $.ajax({
        type:"put",
        url: BackUrl+url,
        data: data,
        headers:{"Authorization":tokenHead+token},
        async: false, //同步
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        xhrFields: {
            withCredentials: true //允许跨域带Cookie
        },
        success: function(data) {
            result=data;
            if(result.code==401){
                window.location="login.html";
            }
        }
    });
    return result;
}

function myGetTokenAjax(url,data) {
    let BackUrl="http://localhost:9000/"
    let tokenHead=localStorage.getItem("tokenHead");
    let token=localStorage.getItem("token");
    let result = null;
    $.ajax({
        type:"get",
        url: BackUrl+url,
        data: data,
        headers:{"Authorization":tokenHead+token},
        async: false, //同步
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        xhrFields: {
            withCredentials: true //允许跨域带Cookie
        },
        success: function(data) {
            result=data;
            if(result.code==401){
                window.location="login.html";
            }
        }
    });
    return result;
}

function myDeleteTokenAjax(url,data) {
    let BackUrl="http://localhost:9000/"
    let tokenHead=localStorage.getItem("tokenHead");
    let token=localStorage.getItem("token");
    let result = null;
    $.ajax({
        type:"delete",
        url: BackUrl+url,
        data: data,
        headers:{"Authorization":tokenHead+token},
        async: false, //同步
        //contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        xhrFields: {
            withCredentials: true //允许跨域带Cookie
        },
        success: function(data) {
            result=data;
            if(result.code==401){
                window.location="login.html";
            }
        }
    });
    return result;
}

