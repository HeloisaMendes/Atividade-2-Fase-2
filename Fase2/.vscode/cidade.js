// Tela ficha médica 

var porta = 2424
let cidadeId = null
let idEstado = null;

let isUpdating = null;

//Quando o documento abrir ele faz essa função
$(document).ready(function() {
    
    // Carregar os estados quando a página estiver pronta
    carregaCmbEstado();
    limpaTela();

    // Detectar quando o usuário escolhe um estado
   $('#cmbEstado').on('change', function() {
       // Obter o valor do estado selecionado
       idEstado = $(this).val();

       // Verificar se um estado foi escolhido
       if(idEstado) {
           // Execute uma ação com base no estado escolhido

        } else {
           alert("Nenhum estado selecionado.");
        }
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


//Clicou na lupa
 document.getElementById('lupa').addEventListener('click', function() {

    const cidadeInput = document.getElementById("nome");
    const nomeCidade = cidadeInput.value;

    if (!nomeCidade) {

        alert('Por favor, insira uma cidade para pesquisar.');
        cidadeInput.focus();
        return;
    }

    pesquisarCidadePeloEstado(nomeCidade)

});


document.getElementById('formulario').addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o envio do formulário

    // Obtém os valores dos campos do formulário
    const estado = document.getElementById('cmbEstado').value;
    const nomeCidade = document.getElementById('nome').value;
    const pesoUnitario = document.getElementById('pesoUnitario').value;
    const valorUnitario = document.getElementById('valorUnitario').value;

    // Verifica se todos os campos estão preenchidos
    if (estado && nomeCidade && pesoUnitario && valorUnitario) {

        if(isUpdating != null){

            //testo se a variavel update está false ou true 
            if (isUpdating) {
                // Se estamos atualizando, chama a função de atualização
                atualizar(estado, nomeCidade, pesoUnitario, valorUnitario);

            } else {
                // Se não estamos atualizando, chama a função de adição
                adicionar(estado, nomeCidade, pesoUnitario, valorUnitario);
            }

        } else {

            alert("Pesquise se essa cidade já está cadastrada em nossa base de dados!")
            const cidadeInput = document.getElementById("nome");
            cidadeInput.focus();
        }
        
    } else {
        alert("Por favor, preencha todos os campos!");
    }
});

// Função para adicionar um novo estado
function adicionar(estado, nomeCidade, pesoUnitario, valorUnitario) {
    $.ajax({
        url: "http://localhost:" + porta + "/cidade/inserir",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            nomeCid: nomeCidade,
            precoUnitPeso: pesoUnitario,
            precoUnitValor: valorUnitario,
            estado: {id: estado}
        }),
        success: function(resposta) {

            //Preenche o id uf, quando um novo UF é criado
            cidadeId = resposta.id

            alert("Nova cidade adicionada com sucesso!");

            cidadeId = null;
            limpaTela()
            carregaCmbEstado()
        },
        error: function() {
            alert("Erro ao adicionar a nova cidade.");
            limpaTela()
        }
    });
}


// Função para atualizar os dados do estado
function atualizar(estado, nomeCidade, pesoUnitario, valorUnitario) {
    $.ajax({
        url: "http://localhost:" + porta + "/cidade/atualizar", 
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            id: cidadeId,
            nomeCid: nomeCidade,
            precoUnitPeso: pesoUnitario,
            precoUnitValor: valorUnitario,
            estado: {id: estado}
        }),
        success: function() {
            alert("Dados da cidade atualizados com sucesso!");
            limpaTela()
            carregaCmbEstado()
        },
        error: function() {
            alert("Erro ao atualizar os dados da cidade.");
            limpaTela()
            carregaCmbEstado;
        }
    });
}

//Função para pesquisar uma cidade em determinado estado
function pesquisarCidadePeloEstado(nomeCidade){

    $.ajax({
        url: "http://localhost:" + porta + "/cidade/estado/" + idEstado + "/cidade/" + nomeCidade,
        type: 'GET', 
       
        success: function(data) {

            if(!data || Object.keys(data).length === 0) {
    
                alert('Cidade não encontrada neste estado. Você pode cadastrar uma nova cidade.');
                // Mudar o estado para adicionar
                isUpdating = false;
        
            } else {

                // Se a cidade existir, preencha os campos com os dados retornados
                cidadeId = data.id;
                $('#pesoUnitario').val(data.precoUnitPeso);
                $('#valorUnitario').val(data.precoUnitValor);

                // Mudar o estado para atualizar
                isUpdating = true;
            }
        },
        error: function(msg){
            alert("Erro de busca... Consulta por cidade")
          }
    });
}

function excluirCidade() {

    if (cidadeId != null) { 

        // Quer dizer que a cidade já foi pesquisada
        $.ajax({
            url :  "http://localhost:" + porta + "/cidade/excluir/" + cidadeId,
            type: "DELETE",
    
    
            success: function(msg){
        
            alert("Cidade excluída com sucesso!")
        
                limpaTela();
                carregaCmbEstado()
                estadoId = null;
                
            },
            error: function(msg) {
                alert("Erro de exclusão...")
            }
            
        });
        
    } else {
        alert("Você deve pesquisar a cidade para depois excluir");
        const cidadeInput = document.getElementById("nome");
        cidadeInput.focus();
    }
    
}

function limpaTela() {

    // Define todos os campos de entrada como vazios
    document.getElementById('nome').value = '';
    document.getElementById('pesoUnitario').value = '';
    document.getElementById('valorUnitario').value = '';
    
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