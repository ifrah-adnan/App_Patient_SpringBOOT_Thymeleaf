package ma.adnan.web;

import jakarta.validation.Valid;
import ma.adnan.entite.Patient;
import ma.adnan.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@Controller
public class PatientController {
    @Autowired
 private PatientRepository patientRepository;
 @GetMapping("/user/index")
    private String DisplayPatient(Model model , @RequestParam(name = "page" ,defaultValue = "0") int page,@RequestParam(name = "size" ,defaultValue = "5") int size,@RequestParam(name = "keyword",defaultValue = "") String keyword){
     Page<Patient> patients=patientRepository.findByNameContains(keyword,PageRequest.of(page,size));
     model.addAttribute("patients",patients);
     model.addAttribute("pages",new int[patients.getTotalPages()]);
     model.addAttribute("currentPage",page);
     model.addAttribute("keyword",keyword);
     return "patients";
 }
 @GetMapping("/admin/delete")
 public String DeletePatient(@RequestParam(name = "id" ,defaultValue = "0") Long id){
  patientRepository.deleteById(id);
  return "redirect:/user/index";
 }
 @GetMapping ("/admin/formPatients")
public String ShowFormPatient(Model model){
model.addAttribute("patient",new Patient());
  return "formPatients";
 }
 @PostMapping("/admin/save")
 public String savePatient( @Valid Patient patient, BindingResult bindingResult,@RequestParam(name = "page" ,defaultValue = "0") int page, @RequestParam(name = "keyword",defaultValue = "") String keyword) {
     if(bindingResult.hasErrors())return "formPatients";
  System.out.println(patient.getScore());
  System.out.println(patient.getDateNaissance());
  System.out.println(patient.getName());
  patientRepository.save(patient);
  return "redirect:/user/index?page="+page+"&keyword="+keyword;
 }
 @GetMapping("/")
 public  String redirect(){
     return "redirect:/user/index";
 }
 @GetMapping("/admin/deleteAll")
 public String SupprimerTOus(Model model){
  patientRepository.deleteAll();
  return "redirect:/user/index";

 }
@GetMapping("/admin/formupdate")
public String update(Model model, Long id,@RequestParam(name = "page" ,defaultValue = "0") int page, @RequestParam(name = "keyword",defaultValue = "") String keyword){
  Patient patient=patientRepository.findById(id).orElse(null);
  if(patient==null) throw new RuntimeException("Patient introuvable");
  model.addAttribute("patient",patient);
  model.addAttribute("page",page);
  model.addAttribute("keyword",keyword);
  return "updateForm";

 }



}
