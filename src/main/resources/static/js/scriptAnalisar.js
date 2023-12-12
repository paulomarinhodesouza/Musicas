$(document).ready(function(){
    $("#successMessage").hide();
    $("#errorMessage").hide();
    $("#selectMusic").select2();
    buscarMusicas();
    $("#formAnalysis").submit(function(event){
        let idMusic = $("#selectMusic").val();
        let analise = $("#analise").val();

        if(!idMusic || !analise){
            $("#errorMessage").show();
            $("#errorMessage").text("Preencha todos os dados");
        }else{
            $("#errorMessage").hide();
            let analysis = {
                idMusic: idMusic,
                analysis: analise 
            }
            cadastarAnalise(analysis);
        }
        return false;
    });
});
function cadastarAnalise(analise){
    $.ajax({
        url: 'http://localhost:8080/analysis/add',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(analise),
        success: function(){
            $("#successMessage").show();
            $("#successMessage").text("Análise cadastrada com sucesso");
            $("#analise").val('');
        },
        error: function(){
            $("#successMessage").hide();
            $("#errorMessage").show();
            $("#errorMessage").text("Erro ao enviar análise, verifique as informações digitadas");
        }
    });
}
function buscarMusicas(){
    $.ajax({
        url:'http://localhost:8080/music',
        type:'GET'
    }).then(function(data){
        let musicSelect = $("#selectMusic");
        for(let i = 0; i < data.length; i++){
            let music = data[i];

            let option = new Option(music.title, music.id, true, true);
            musicSelect.append(option).trigger('change');

            musicSelect.trigger({
                type: 'select2:select',
                params: {
                    data: music.id
                }
            });
        }        
    });
    
}