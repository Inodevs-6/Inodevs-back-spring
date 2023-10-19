package com.inodevs.app.service;

import com.inodevs.app.entity.Empresa;

public interface IEmpresaService {

    public Empresa buscarEmpresa(Long id);

    public Empresa novaEmpresa (Empresa empresa);

}
