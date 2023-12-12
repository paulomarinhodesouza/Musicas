$(document).ready(function(){
    $("#successMessage").hide();
    $("#errorMessage").hide();
    $("#errorMessageExcluir").hide();
    $("#successMessageExcluir").hide();
    $("#selectMusic").select2();
    buscarMusicas();

    $("#formSelectMusic").submit(function(event){
        let idMusic = $("#selectMusic").val();
        buscaAnálise(idMusic);
        return false;
    });

    $("#btnAtualizar").click(function(event){
        let id = $("#id").val();
        let idMusic = $("#musica").val();
        let analise = $("#analise").val();
        let analysis = {
            idMusic: idMusic,
            analysis: analise
        }
        editarAnalise(id, analysis);
    });

    $(".btnFechar").click(function(event){
        let idMusic = $("#musica").val();
        buscaAnálise(idMusic);
    });

    $(".btnCancelar").click(function(event){
        let idMusic = $("#idMusicExcluir").val();
        buscaAnálise(idMusic);
    });

    $("#btnExcluir").click(function(event){
        let id = $("#idExcluir").val();
        
        excluirAnalise(id);
    });
});

function excluirAnalise(id){
    $.ajax({
        url:'http://localhost:8080/analysis/' + id,
        type:'DELETE',
        success:function(){
            $("#errorMessageExcluir").hide();
            $("#successMessageExcluir").show();
            $("#successMessageExcluir").text("Análise excluida com sucesso");
        },
        error:function(){
            $("#successMessageExcluir").hide();
            $("#errorMessageExcluir").show();
            $("#errorMessageExcluir").text("Erro ao excluir análise");
            
        }
    });
}

function excluirAnaliseModal(id){
    $.ajax({
        url:'http://localhost:8080/analysis/' + id,
        type:'GET',
        success:function(data){
            $("#idExcluir").val(data.id);
            $("#idMusicExcluir").val(data.music.id);
        }
    });
}

function editarAnalise(id, analysis){
    $.ajax({
        url:'http://localhost:8080/analysis/' + id,
        type:'PUT',
        contentType: 'application/json',
        data: JSON.stringify(analysis),
        success:function(){
            $("#errorMessage").hide();
            $("#successMessage").show();
            $("#successMessage").text("Análise editada com sucesso");
        },
        error:function(){
            $("#successMessage").hide();
            $("#errorMessage").show();
            $("#errorMessage").text("Erro ao editar análise, verifique as informações");
            
        }
    });
}

function editarAnaliseModal(id){
    $("#successMessage").hide();
    $("#errorMessage").hide();
    $.ajax({
        url:'http://localhost:8080/analysis/' + id,
        type:'GET',
        success:function(data){
            $("#id").val(data.id);
            $("#musica").val(data.music.id)
            $("#analise").val(data.analysis);
        }
    });
}
function buscaAnálise(idMusic){
    
    $.ajax({
        url:'http://localhost:8080/analysis/music/' + idMusic,
        type:'GET',
        success:function(data){

            $("#titleAnalises").text("");
            $("#analises").html("");
            for(let i=0; i < data.length; i++){
                let analise = data[i];
                $("#titleAnalises").text("Análises de " + analise.music.title);
                let id = i + 1;
                $("#analises").append("<h5 class='title-page'>"+ id + " - " + analise.analysis +"</h5>"+
                "<button data-bs-toggle='modal' data-bs-target='#analiseModal' onclick='editarAnaliseModal("+ analise.id +")' style='margin: 10px; margin: 10px;' class='btn btn-primary'>Editar</button>"+
                "<button data-bs-toggle='modal' data-bs-target='#excluirModel' onclick='excluirAnaliseModal("+ analise.id +")' style='margin: 10px;' class='btn btn-danger'>Excluir</button>");
            }
            
        },
        error: function(){
            $("#titleAnalises").text("");
            $("#analises").html("");
            $("#titleAnalises").text("Nenhuma análise encontrada");
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