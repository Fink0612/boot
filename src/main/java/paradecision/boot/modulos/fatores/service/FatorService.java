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

  public String insertFator(Fator oFatorModel) {
    String res = this.fatorRepository.insertFator(oFatorModel);
    return res;
  }

  public Fator selectFator(Fator oFatorModel) {
    oFatorModel = this.fatorRepository.selectFator(oFatorModel);
    return oFatorModel;
  }

  public String updateFator(Fator oFatorModel) {
    String res = this.fatorRepository.updateFator(oFatorModel);
    return res;
  }
}
