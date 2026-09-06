package paradecision.boot.modulos.fatores.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.fatores.entity.Fator;
import paradecision.boot.modulos.fatores.repository.FatorRepository;

@Service
public class FatorService {
  private final FatorRepository fatorRepository;

  public FatorService(FatorRepository fatorRepository) {
    this.fatorRepository = fatorRepository;
  }

  public String insertFator(Fator dadosFator) {
    String resultadoProcessamento = this.fatorRepository.insertFator(dadosFator);
    return resultadoProcessamento;
  }

  public Fator selectFator(Fator dadosFator) {
    dadosFator = this.fatorRepository.selectFator(dadosFator);
    return dadosFator;
  }

  public String updateFator(Fator dadosFator) {
    String resultadoProcessamento = this.fatorRepository.updateFator(dadosFator);
    return resultadoProcessamento;
  }
}
