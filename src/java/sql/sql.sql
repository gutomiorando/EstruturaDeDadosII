/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Usuario
 * Created: 05/12/2017
 */



-- -----------------------------------------------------
-- Table `mydb`.`cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cidade (
  codcid SERIAL,
  nome VARCHAR(100) NOT NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (codcid));

-- -----------------------------------------------------
-- Table `mydb`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cliente (
  codcli SERIAL,
  codcid SERIAL,
  tipocadastro CHAR(1) NULL,
  nome VARCHAR(100) NOT NULL,
  cpf_cnpj VARCHAR(18) NOT NULL,
  rua VARCHAR(100) NOT NULL,
  numero INT NOT NULL,
  datacadastro DATE NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (codcli),
  CONSTRAINT fk_cliente_cidade1
    FOREIGN KEY (codcid)
    REFERENCES cidade (codcid));
    
-- -----------------------------------------------------
-- Table `mydb`.`empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS empresa (
  idempresa SERIAL,
  descricao VARCHAR(100) NULL,
  site VARCHAR(100) NULL,
  codcid INT NOT NULL,
  PRIMARY KEY (idempresa),
  CONSTRAINT fk_empresa_cidade1
    FOREIGN KEY (codcid)
    REFERENCES cidade (codcid));
    
-- -----------------------------------------------------
-- Table `mydb`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS funcionario (
  codfunc SERIAL,
  idempresa SERIAL,
  nome VARCHAR(100) NOT NULL,
  dataadmissao TIMESTAMP NULL,
  datademissao TIMESTAMP NULL,
  funcao VARCHAR(100) NOT NULL,
  salario DOUBLE PRECISION NOT NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (codfunc),
  CONSTRAINT fk_funcionario_empresa1
    FOREIGN KEY (idempresa)
    REFERENCES empresa (idempresa));
    
-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
  codusuario SERIAL,
  nomeusuario VARCHAR(100) NULL,
  email VARCHAR(100) NOT NULL,
  senha VARCHAR(100) NOT NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (codusuario));

  
-- -----------------------------------------------------
-- Table `mydb`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS venda (
  codvenda SERIAL,
  codcli SERIAL,
  codusuario SERIAL,
  idempresa SERIAL,
  motorista VARCHAR(100) NULL,
  placa VARCHAR(8) NULL,
  quantidade DOUBLE PRECISION NULL,
  litros DOUBLE PRECISION NULL,
  valortotal DOUBLE PRECISION NULL,
  formapagamento CHAR(1) NULL,
  dataemissao TIMESTAMP NULL,
  situacao VARCHAR(1) NULL,
  PRIMARY KEY (codvenda),
  CONSTRAINT fk_cupom_cliente1
    FOREIGN KEY (codcli)
    REFERENCES cliente (codcli),
  CONSTRAINT fk_venda_empresa1
    FOREIGN KEY (idempresa)
    REFERENCES empresa (idempresa),
  CONSTRAINT fk_venda_usuario1
    FOREIGN KEY (codusuario)
    REFERENCES usuario (codusuario));

-- -----------------------------------------------------
-- Table `mydb`.`mercadoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mercadoria (
  codmercadoria SERIAL,
  descricao VARCHAR(100) NOT NULL,
  valorunitario DOUBLE PRECISION NOT NULL,
  quantidade DOUBLE PRECISION NOT NULL,
  estoque DOUBLE PRECISION NOT NULL,
  horaatualizacao TIMESTAMP NULL,
  tipo CHAR(1) NOT NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (codmercadoria));

-- -----------------------------------------------------
-- Table `mydb`.`itensvenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS itensvenda (
  coditen SERIAL,
  codvenda SERIAL,
  codmercadoria SERIAL,
  quantidade DOUBLE PRECISION NOT NULL,
  valorunitario DOUBLE PRECISION NULL,
  PRIMARY KEY (coditen),
  CONSTRAINT fk_itensvenda_venda1
    FOREIGN KEY (codvenda)
    REFERENCES venda (codvenda),
  CONSTRAINT fk_itensvenda_combustivel1
    FOREIGN KEY (codmercadoria)
    REFERENCES mercadoria (codmercadoria));

	
-- -----------------------------------------------------
-- Table `mydb`.`contasareceber`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS contasareceber (
  id SERIAL,
  codvenda INT NOT NULL,
  datalancamento TIMESTAMP NULL,
  datavencimento TIMESTAMP NULL,
  datapagamento TIMESTAMP NULL,
  valorprevisto DOUBLE PRECISION NULL,
  valorrecebido DOUBLE PRECISION NULL,
  situacao CHAR(1) NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_contasareceber_venda1
    FOREIGN KEY (codvenda)
    REFERENCES venda (codvenda));
    

-- -----------------------------------------------------
-- Table `mydb`.`fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS fornecedor (
  codfornecedor SERIAL,
  nome VARCHAR(100) NOT NULL,
  datacadastro DATE NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (codfornecedor));

-- -----------------------------------------------------
-- Table `mydb`.`contasapagar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS contasapagar (
  idconta SERIAL,
  codfornecedor INT NOT NULL,
  descricao VARCHAR(100) NULL,
  dataprevista TIMESTAMP NULL,
  datapagamento TIMESTAMP NULL,
  valortotal DOUBLE PRECISION NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (idconta),
  CONSTRAINT fk_contasapagar_fornecedor1
    FOREIGN KEY (codfornecedor)
    REFERENCES fornecedor (codfornecedor));
	
	
insert into usuario values
(default,'admin','gutomiorando@yahoo.com.br','<2fcc1dd51cb7514a99f03debf513ca7af3b25669>','A');
insert into cidade values (default,'Lajeado','A');
insert into empresa values(1,'Posto Confianca','www.postoconficars.com.br','1');
insert into cliente values(default, '1','F','Anonimo','326.159.219-24','dos bobos', '10','2017-12-01','A');
insert into mercadoria values(default, 'Gasolina Comum', '4','0','1000', '2017-12-05', 'C','A'); 
insert into mercadoria values(default, 'Gasolina Aditivada', '5','0','1000', '2017-12-05', 'C','A');