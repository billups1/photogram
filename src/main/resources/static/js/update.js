// (1) 회원정보 수정
function update(principalId, event) {
    event.preventDefault();

    let data = $("#profileUpdate").serialize();

    console.log("data", data);

    $.ajax({
        type:"put",
        url:"/user/update",
        data: data,
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json"
    }).done(res=>{
        console.log("회원업데이트 성공", res);
        location.href=`/user/${principalId}`
    }).fail(error=>{
        console.log("회원업데이트 실패", error);
    });

}