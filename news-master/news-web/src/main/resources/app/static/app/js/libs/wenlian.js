
function revelationid(id,phone,name){
    uid = id;
    if(uid){
        $.ajax({
            type: "POST",
            url: setUidUrl,
            data: {uid:uid,phone:phone,name:name},
            dataType: "json",
            success:function(res){
                if(res.error_code==0) {
                    location.reload();
                }
            }
        });
    }

}