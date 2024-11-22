var porta = 2424
let idCidade = null
let idEstado = null;
let idFuncionario = null;

let isUpdating = null;


//Quando o documento abrir ele faz essa função
$(document).ready(function() {
    
    // Carregar os estados quando a página estiver pronta
    carregaCmbEstado();

    // Detectar quando o usuário escolhe um estado
   $('#cmbEstado').on('change', function() {
       // Obter o valor do estado selecionado
       idEstado = $(this).val();

       // Verificar se um estado foi escolhido
       if(idEstado) {
           // Execute uma ação com base no estado escolhido
           carregarCmbCidade(idEstado);

        } else {
           alert("Nenhum estado selecionado.");
        }
   });


   $('#cmbCidade').on('change', function() {

        idCidade = $(this).val();

    });

});


function carregaCmbEstado() {

   $.ajax({
   
       url:"http://localhost:" + porta + "/estado/consultaTodos",
       type: 'get',
       data: {},
   
       success: function(msg) {

        if(Object.keys(msg).length === 0) {
   
           alert("Sem estados cadastrados");
   
        } else {

           let opcoes;
   
           for(let i = 0; i < msg.length; i++) { 
               opcoes += mostraLinhaCmbEstado(msg[i]);
           }  
   
           //Adiciona as linhas na tabela
           $('#cmbEstado').html(opcoes);

           // Seleciona o primeiro estado automaticamente e dispara o evento "change"
           $("#cmbEstado").prop('selectedIndex', 0).trigger('change'); 
        }
       },
       error: function(msg){
         alert("Erro de busca...")
       }
   });
}


function mostraLinhaCmbEstado(data) {
    return (
      '<option value="'+ data.id + '">' + data.nomeEst +'</option>'
    );
}


function carregarCmbCidade(idEstado) {

    $.ajax({

        url: "http://localhost:" + porta + "/cidade/estado/" + idEstado,
        type: 'GET', 
       
        success: function(msg) {

            if(Object.keys(msg).length === 0) {
      
                alert("Sem cidades cadastradas desse estado!");
      
            } else {
      
                let opcoes;
        
                for(let i = 0; i < msg.length; i++) { 
                    opcoes += mostraLinhaCmbCidade(msg[i]);
                }  
        
                //Adiciona as linhas na tabela
                $('#cmbCidade').html(opcoes);

                // Seleciona o primeira cidade automaticamente e dispara o evento 'change'
                $('#cmbCidade').prop('selectedIndex', 0).trigger('change');
  
            }
          },
          error: function(msg){
            alert("Erro de busca... cidade")
          }
    });
}

function mostraLinhaCmbCidade(data) {
    return (
      '<option value="'+ data.id + '">' + data.nomeCid +'</option>'
    );
}


//Clicou na lupa
document.getElementById('lupa').addEventListener('click', function() {

    const nomeInput = document.getElementById("nome");
    const nome = nomeInput.value;

    if (!nome) {

        alert('Por favor, insira um nome para pesquisar.');
        nomeInput.focus();
        return;
    }

    pesquisarNome(nome)

});


//Função para pesquisar pelo CPF
function pesquisarNome(nome){
    $.ajax({
        url: "http://localhost:" + porta + "/funcionario/cidade/" + idCidade + "/nome/" + nome,
        type: 'GET', 
       
        success: function(data) {

            if(!data || Object.keys(data).length === 0) {
    
                alert('Nome não encontrado. Você pode cadastrar um novo funcionário.');
                // Mudar o estado para adicionar
                isUpdating = false;
        
            } else {

                alert("Esse funcionário já existe, prossiga caso queria atualizar o cadastro");

                // Se o CPF existir, preencha os campos com os dados retornados
                idFuncionario = data.id;
                

                // Mudar o estado para atualizar
                isUpdating = true;
            }
        },
        error: function(msg){
            alert("Erro de busca... Consulta por CPF")
          }
    });
}


document.getElementById('formulario').addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o envio do formulário

    // Obtém os valores dos campos do formulário
    const nome = document.getElementById("nome").value;

    // Verifica se todos os campos estão preenchidos
    if (nome) {

        if(isUpdating != null){

            //testo se a variavel update está false ou true 
            if (isUpdating) {
                // Se estamos atualizando, chama a função de atualização
                atualizar(idCidade, nome);

            } else {
                // Se não estamos atualizando, chama a função de adição
                adicionar(idCidade, nome);
            }

        } else {

            alert("Pesquise se o nome já está cadastrado em nossa base de dados!")
            const nomeInput = document.getElementById("nome");
            nomeInput.focus();
        }
        
    } else {
        alert("Por favor, preencha todos os campos!");
    }
});


// Função para adicionar um novo funcionário
function adicionar(idCidade, nome) {
    $.ajax({
        url: "http://localhost:" + porta + "/funcionario/inserir",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            nomeFunc: nome,
            cidade: { id: idCidade}
        }),
        success: function(resposta) {

            //Preenche o id funcionário, quando um novo funcionário é criado
            idFuncionario = resposta.id

            alert("Novo funcionário adicionado com sucesso!");

            idFuncionario = null;
            limpaTela()
        },
        error: function() {
            alert("Erro ao adicionar o novo funcionário.");
            limpaTela()
        }
    });
}


// Função para atualizar os dados do cliente
function atualizar(idCidade, nome) {
    $.ajax({
        url: "http://localhost:" + porta + "/funcionario/atualizar", 
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            id: idFuncionario,
            nomeFunc: nome,
            cidade: { id: idCidade}
        }),
        success: function() {
            alert("Dados do funcionário atualizados com sucesso!");
            limpaTela()
        },
        error: function() {
            alert("Erro ao atualizar os dados do funcionario.");
            limpaTela()
        }
    });
}

function excluirFuncionario() {

    if (idFuncionario != null) { 

        // Quer dizer que o funcionário já foi pesquisado
        $.ajax({
            url :  "http://localhost:" + porta + "/funcionario/excluir/" + idFuncionario,
            type: "DELETE",
    
    
            success: function(msg){
        
            alert("Funcionário excluído com sucesso!")
        
                limpaTela();
                idFuncionario = null;
                
            },
            error: function(msg) {
                alert("Erro de exclusão...")
            }
            
        });
        
    } else {
        alert("Você deve pesquisar o nome para depois excluir");
        const nomeInput = document.getElementById("nome");
        nomeInput.focus();
    }
    
}

function limpaTela() {

    // Define todos os campos de entrada como vazios
    carregaCmbEstado();
    document.getElementById('nome').value = '';
    
    //define isUpdating como null novamente
    isUpdating = null;

}



