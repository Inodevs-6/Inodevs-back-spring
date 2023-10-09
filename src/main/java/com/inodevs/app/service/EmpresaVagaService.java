package com.inodevs.app.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inodevs.app.entity.EmpresaVaga;
import com.inodevs.app.repository.EmpresaVagaRepository;

@Service
public class EmpresaVagaService {
    
    @Autowired
    private EmpresaVagaRepository empresaVagaRepo;

    public EmpresaVaga editarVaga (EmpresaVaga empresa_vaga) {
        
        if( empresa_vaga.getConhecimentosEditado() == null &&
            empresa_vaga.getHabilidadesEditado() == null &&
            empresa_vaga.getAtitudesEditado() == null) {
            throw new IllegalArgumentException("Os campos obrigatórios não foram preenchidos!");
        }
        return empresaVagaRepo.save(empresa_vaga);        
    }

    public EmpresaVaga buscarCandidatosPorVagaEditada(Long id) {
        Optional<EmpresaVaga> empresaVagaOp = empresaVagaRepo.findById(id);
        if(empresaVagaOp.isEmpty()) {
            throw new IllegalArgumentException("Vaga não encontrada!");
        }
        return empresaVagaOp.get();
    }

}
