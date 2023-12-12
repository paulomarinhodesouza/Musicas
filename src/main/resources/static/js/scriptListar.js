$(document).ready(function(){
    $("#successMessage").hide();
    $("#errorMessage").hide();
    $("#errorMessageExcluir").hide();
    $("#successMessageExcluir").hide();
    getMusic();

    $("#btnAtualizar").click(function(event){
        let id = $("#id").val();
        let title = $("#nameMusic").val();
        let gender = $("#genderMusic").val();
        let year  = $("#yearMusic").val();
        let music = {
            title: title,
            gender: gender,
            year: year
        }
        editarMusica(id, music);
    });

    $(".btnFechar").click(function(event){
        getMusic();
    });
    $(".btnCancelar").click(function(event){
        getMusic();
    });
    $("#btnExcluir").click(function(event){
        let id = $("#idExcluir").val();
        
        excluirMusica(id);
    });

});
function getMusic(){
    $("#tableMusic").html("");
    $.ajax({
        url:'http://localhost:8080/music',
        type:'GET',
        success: function(data){
            for(let i = 0; i < data.length; i++){
                let music = data[i];
                let id = i + 1;
                $("#tableMusic").append("<tr><td>"+ id +"</td><td>"+
                music.title + "</td><td>"+ music.gender +"</td><td>"+ music.year +"</td><td>"+
                "<button data-bs-toggle='modal' data-bs-target='#musicaModal' onclick='editarMusicaModal("+ music.id +")' style='margin-right: 5px;' class='btn btn-primary'>Editar</button>"+
                "<button data-bs-toggle='modal' data-bs-target='#excluirModal' onclick='excluirMusicaModal("+ music.id +")' class='btn btn-danger'>Excluir</button></td></tr>");
            }
        }
    });
}
function editarMusica(id, music){
    $.ajax({
        url:'http://localhost:8080/music/' + id,
        type:'PUT',
        contentType: 'application/json',
        data: JSON.stringify(music),
        success:function(){
            $("#errorMessage").hide();
            $("#successMessage").show();
            $("#successMessage").text("Musica editada com sucesso");
        },
        error:function(){
            $("#successMessage").hide();
            $("#errorMessage").show();
            $("#errorMessage").text("Erro ao editar musica, verifique as informações");
            
        }
    });
}
function editarMusicaModal(id){
    $("#successMessage").hide();
    $("#errorMessage").hide();
    $.ajax({
        url:'http://localhost:8080/music/' + id,
        type:'GET',
        success:function(data){
            $("#id").val(data.id);
            $("#nameMusic").val(data.title)
            $("#genderMusic").val(data.gender);
            $("#yearMusic").val(data.year);
        }
    });
}

function excluirMusica(id){
    $.ajax({
        url:'http://localhost:8080/music/' + id,
        type:'DELETE',
        success:function(){
            $("#errorMessageExcluir").hide();
            $("#successMessageExcluir").show();
            $("#successMessageExcluir").text("Música excluida com sucesso");
        },
        error:function(){
            $("#successMessageExcluir").hide();
            $("#errorMessageExcluir").show();
            $("#errorMessageExcluir").text("Erro ao excluir música");
            
        }
    });
}

function excluirMusicaModal(id){
    $.ajax({
        url:'http://localhost:8080/music/' + id,
        type:'GET',
        success:function(data){
            $("#idExcluir").val(data.id);
            $("#idMusicExcluir").val(data.music.id);
        }
    });
}