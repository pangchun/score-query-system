function roleDetail(data, index){
    $("#id").val(data.id);
    $("#name").val(data.name);
    $("#description").val(data.description);
    $("input[name=status][value="+data.status+"]").prop("checked",true);
    //在layui中，单选按钮需要重新渲染，否则不出值
    layui.form.render();
}