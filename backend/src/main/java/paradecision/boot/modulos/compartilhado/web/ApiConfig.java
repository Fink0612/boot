package paradecision.boot.modulos.compartilhado.web;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.*;
import paradecision.boot.modulos.compartilhado.service.RegraNegocioException;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
@Configuration
public class ApiConfig implements WebMvcConfigurer {
  @Override public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new HandlerInterceptor(){
      @Override public boolean preHandle(HttpServletRequest req,HttpServletResponse res,Object handler) {
        res.setHeader("Cache-Control","no-store");
        String path=req.getRequestURI().substring(req.getContextPath().length());
        if(!java.util.Set.of("GET","HEAD","OPTIONS").contains(req.getMethod())&&!"XMLHttpRequest".equals(req.getHeader("X-Requested-With")))throw new RegraNegocioException(403,"Envie o cabeçalho X-Requested-With: XMLHttpRequest.");
        if(path.equals("/api/autenticacao/login"))return true;
        var session=req.getSession(false);
        if(session==null||session.getAttribute("usuarioId")==null)throw new RegraNegocioException(401,"Entre para continuar.");
        return true;
      }
    }).addPathPatterns("/api/**");
  }
  @Bean OpenAPI openAPI(){return new OpenAPI().info(new Info().title("ParaDecision API").version("1.0").description("Faça login em /api/autenticacao/login. O cookie de sessão identifica o usuário. Em Authorize, informe XMLHttpRequest para o cabeçalho de proteção das escritas."))
    .components(new Components().addSecuritySchemes("ProtecaoEscrita",new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("X-Requested-With")))
    .addSecurityItem(new SecurityRequirement().addList("ProtecaoEscrita"));}
}
