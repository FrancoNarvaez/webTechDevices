package um.edu.ar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static um.edu.ar.domain.AdicionalTestSamples.*;
import static um.edu.ar.domain.DispositivoTestSamples.*;

import org.junit.jupiter.api.Test;
import um.edu.ar.web.rest.TestUtil;

class AdicionalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Adicional.class);
        Adicional adicional1 = getAdicionalSample1();
        Adicional adicional2 = new Adicional();
        assertThat(adicional1).isNotEqualTo(adicional2);

        adicional2.setId(adicional1.getId());
        assertThat(adicional1).isEqualTo(adicional2);

        adicional2 = getAdicionalSample2();
        assertThat(adicional1).isNotEqualTo(adicional2);
    }

    @Test
    void dispositivoTest() {
        Adicional adicional = getAdicionalRandomSampleGenerator();
        Dispositivo dispositivoBack = getDispositivoRandomSampleGenerator();

        adicional.setDispositivo(dispositivoBack);
        assertThat(adicional.getDispositivo()).isEqualTo(dispositivoBack);

        adicional.dispositivo(null);
        assertThat(adicional.getDispositivo()).isNull();
    }
}
