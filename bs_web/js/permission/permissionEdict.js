function permissionDetail(data, index){
    $("#id").val(data.id);
    $("#name").val(data.name);
    $("#value").val(data.value);
    $("input[name=status][value="+data.status+"]").prop("checked",true);
    //在layui中，单选按钮需要重新渲染，否则不出值
    layui.form.render();
}