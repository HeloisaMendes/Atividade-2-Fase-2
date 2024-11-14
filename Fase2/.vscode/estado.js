// Tela ficha médica 

var porta = 2424
let estadoId = null

let isUpdating = null;

//Clicou na lupa
 document.getElementById('lupa').addEventListener('click', function() {

    const ufInput = document.getElementById("uf");
    const uf = ufInput.value;

    if (!uf) {

        alert('Por favor, insira um UF para pesquisar.');
        ufInput.focus();
        return;
    }

    pesquisarUf(uf)

});


document.getElementById('formulario').addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o envio do formulário

    // Obtém os valores dos campos do formulário
    const uf = document.getElementById('uf').value;
    const nome = document.getElementById('nome').value;
    const icmsDentro = document.getElementById('icmsDentro').value;
    const icmsFora = document.getElementById('icmsFora').value;

    // Verifica se todos os campos estão preenchidos
    if (uf && nome && icmsDentro && icmsFora) {

        if(isUpdating != null){

            //testo se a variavel update está false ou true 
            if (isUpdating) {
                // Se estamos atualizando, chama a função de atualização
                atualizar(uf, nome, icmsDentro, icmsFora);

            } else {
                // Se não estamos atualizando, chama a função de adição
                adicionar(uf, nome, icmsDentro, icmsFora);
            }

        } else {

            alert("Pesquise se o UF já está cadastrado em nossa base de dados!")
            const ufInput = document.getElementById("uf");
            ufInput.focus();
        }
        
    } else {
        alert("Por favor, preencha todos os campos!");
    }
});

// Função para adicionar um novo estado
function adicionar(uf, nome, icmsDentro, icmsFora) {
    $.ajax({
        url: "http://localhost:" + porta + "/estado/inserir",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            uf: uf,
            nomeEst: nome,
            icmsLocal: icmsDentro,
            icmsOutroUf: icmsFora
        }),
        success: function(resposta) {

            //Preenche o id uf, quando um novo UF é criado
            estadoId = resposta.id

            alert("criei um estado " + estadoId)

            alert("Novo estado adicionado com sucesso!");

            estadoId = null;
            limpaTela()
        },
        error: function() {
            alert("Erro ao adicionar o novo estado.");
            limpaTela()
        }
    });
}


// Função para atualizar os dados do estado
function atualizar(uf, nome, icmsDentro, icmsFora) {
    $.ajax({
        url: "http://localhost:" + porta + "/estado/atualizar", 
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            id: estadoId,
            uf: uf,
            nomeEst: nome,
            icmsLocal: icmsDentro,
            icmsOutroUf: icmsFora
        }),
        success: function() {
            alert("Dados do estado atualizados com sucesso!");
            limpaTela()
        },
        error: function() {
            alert("Erro ao atualizar os dados do estado.");
            limpaTela()
        }
    });
}

//Função para pesquisar pelo UF
function pesquisarUf(uf){
    $.ajax({
        url: "http://localhost:" + porta + "/estado/consultaPorUf/" + uf,
        type: 'GET', 
       
        success: function(data) {

            if(!data || Object.keys(data).length === 0) {
    
                alert('UF não encontrado. Você pode cadastrar um novo estado.');
                // Mudar o estado para adicionar
                isUpdating = false;
        
            } else {

                // Se o UF existir, preencha os campos com os dados retornados
                estadoId = data.id;
                $('#nome').val(data.nomeEst);
                $('#icmsDentro').val(data.icmsLocal);
                $('#icmsFora').val(data.icmsOutroUf);

                // Mudar o estado para atualizar
                isUpdating = true;
            }
        },
        error: function(msg){
            alert("Erro de busca... Consulta por UF")
          }
    });
}

function excluirEstado() {

    if (estadoId != null) { 

        // Quer dizer que o estado já foi pesquisado
        $.ajax({
            url :  "http://localhost:" + porta + "/estado/excluir/" + estadoId,
            type: "DELETE",
    
    
            success: function(msg){
        
            alert("Estado excluído com sucesso!")
        
                limpaTela();
                estadoId = null;
                
            },
            error: function(msg) {
                alert("Erro de exclusão...")
            }
            
        });
        
    } else {
        alert("Você deve pesquisar o UF para depois excluir");
        const ufInput = document.getElementById("uf");
        ufInput.focus();
    }
    
}

function limpaTela() {

    // Define todos os campos de entrada como vazios
    document.getElementById('uf').value = '';
    document.getElementById('nome').value = '';
    document.getElementById('icmsDentro').value = '';
    document.getElementById('icmsFora').value = '';
    
    //define isUpdating como null novamente
    isUpdating = null;

}


function carregaCmbEspecialidade() {

    $.ajax({
    
        url:"http://localhost:" + porta + "/especialidade/consultaTodos",
        type: 'get',
        data: {},
    
        success: function(msg) {

            if(Object.keys(msg).length === 0) {

                alert("Sem Especialidades cadastradas");

            } else {

                let opcoes;

                for(let i = 0; i < msg.length; i++) { 
                    opcoes += mostraLinhaCmbEspecialidades(msg[i]);
                }  

                //Adiciona as linhas na tabela
                $('#cmbEspecialidade').html(opcoes);

                // Seleciona a primeira especialidade automaticamente e dispara o evento 'change'
                $('#cmbEspecialidade').prop('selectedIndex', 0).trigger('change');

            }
        },
        error: function(msg){
          alert("Erro de busca... Especialidade")
        }
    });
}