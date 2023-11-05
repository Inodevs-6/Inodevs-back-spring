package com.inodevs.app.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.inodevs.app.entity.EmpresaVaga;
import com.inodevs.app.entity.Vaga;
import com.inodevs.app.entity.Empresa;
import com.inodevs.app.repository.EmpresaVagaRepository;
import com.inodevs.app.repository.EmpresaRepository;
import com.inodevs.app.repository.VagaRepository;

@Service
public class EmpresaVagaService {
    
    @Autowired
    private EmpresaVagaRepository empresaVagaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired
    private VagaRepository vagaRepo;

    @PreAuthorize("isAuthenticated")
    public EmpresaVaga editarVaga(Long emp_id, Long vaga_id, Vaga vagas) {
        
        Optional<EmpresaVaga> empresaVagas = empresaVagaRepo.findByEmpresaIdAndVagaId(emp_id, vaga_id);

        if (empresaVagas.isEmpty()) {

            Optional<Empresa> empresa = empresaRepo.findById(emp_id);

            Optional<Vaga> vaga = vagaRepo.findById(vaga_id);

            EmpresaVaga empresaVaga = new EmpresaVaga(vaga.get(), empresa.get());
        
            empresaVaga.setConhecimentosEditado(vagas.getConhecimentos());
            empresaVaga.setHabilidadesEditado(vagas.getHabilidades());
            empresaVaga.setAtitudesEditado(vagas.getAtitudes());

            return empresaVagaRepo.save(empresaVaga);    
        }

        empresaVagas.get().setConhecimentosEditado(vagas.getConhecimentos());
        empresaVagas.get().setHabilidadesEditado(vagas.getHabilidades());
        empresaVagas.get().setAtitudesEditado(vagas.getAtitudes());

        return empresaVagaRepo.save(empresaVagas.get());  
    }

    @PreAuthorize("isAuthenticated")
    public EmpresaVaga buscarCandidatosPorVagaEditada(Long id) {
        Optional<EmpresaVaga> empresaVagaOp = empresaVagaRepo.findById(id);
        if(empresaVagaOp.isEmpty()) {
            throw new IllegalArgumentException("Vaga não encontrada!");
        }
        return empresaVagaOp.get();
    }

    public EmpresaVaga buscarCHAPorVagaEditada(Long empresa, Long vaga) {
        Optional<EmpresaVaga> empresaVagaOp = empresaVagaRepo.findByEmpresaIdAndVagaId(empresa, vaga);
        if(empresaVagaOp.isEmpty()) {
            throw new IllegalArgumentException("Vaga não encontrada!");
        }
        return empresaVagaOp.get();

    }

}
