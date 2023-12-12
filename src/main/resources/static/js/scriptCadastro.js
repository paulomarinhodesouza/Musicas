$(document).ready(function(){
    $("#successMessage").hide();
    $("#errorMessage").hide();

    $("#formMusic").submit(function(event){
        let name = $("#nameMusic").val();
        let gender = $("#genderMusic").val();
        let year = $("#yearMusic").val();

        if(!name || !gender || !year){
            $("#errorMessage").show();
            $("#errorMessage").text("Preencha todos os dados");
        }else{
            $("#errorMessage").hide();
            let music = {
                title: name,
                gender: gender,
                year: year
            };
            cadastroMusica(music);
        }
        return false;
    });
});
function cadastroMusica(music){
    
    $.ajax({
        url: 'http://localhost:8080/music/add',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(music),
        success: function(){
            $("#successMessage").show();
            $("#successMessage").text("Musica cadastrada com sucesso");
            $("#nameMusic").val('');
            $("#genderMusic").val('');
            $("#yearMusic").val('');
        },
        error: function(){
            $("#successMessage").hide();
            $("#errorMessage").show();
            $("#errorMessage").text("Erro ao cadastar musica, verifique as informações digitadas");
        }
    });
}