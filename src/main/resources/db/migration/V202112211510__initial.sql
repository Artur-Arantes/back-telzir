
CREATE TABLE IF NOT EXISTS plano{
id_plan INT NOT NULL ,
nom_plan VARCHAR(255) NOT NULL,
temp_ise_plan BIGINT(20) NOT NULL,
tax_plan FLOAT(5) NOT NULL,
CONSTRAINT pk_plano PRIMARY KEY (id_plan)
} ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS valor_por_minuto{
id_val_min INT NOT NULL ,
ddd_ori_val_min VARCHAR (20) NOT NULL,
ddd_des_val_minVARCHAR(20) NOT NULL
val_min_val_min  FLOAT(5) NOT NULL,
CONSTRAINT pk_valor_por_minuto PRIMARY KEY (id_tab)
} ENGINE=InnoDB DEFAULT CHARSET=utf8;
