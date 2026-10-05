package ressourcerest;

import entities.Etudiant;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("etudiants")
public class restEtudiant {

    public static EtudiantBusiness etB = new EtudiantBusiness();
    public static OptionBusiness optB = new OptionBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEtudiant(Etudiant etudiant) {

        if (etB.addEtudiant(etudiant)) {
            return Response.status(200).build();
        } else {
            return Response.status(404).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEtudiants() {

        List<Etudiant> lE = etB.getAllEtudiants();

        if (lE != null && !lE.isEmpty()) {
            return Response.status(200).entity(lE).build();
        } else {
            return Response.status(404).build();
        }
    }

    @GET
    @Path("{identifiant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantByIdentifiant(
            @PathParam("identifiant") String identifiant) {

        Etudiant e =
                etB.getEtudiantByIdentifiant(identifiant);

        if (e != null) {
            return Response.status(200).entity(e).build();
        } else {
            return Response.status(404).build();
        }
    }

    @DELETE
    @Path("{identifiant}")
    public Response deleteEtudiant(
            @PathParam("identifiant") String identifiant) {

        if (etB.deleteEtudiant(identifiant)) {
            return Response.status(204).build();
        } else {
            return Response.status(404).build();
        }
    }

    @PUT
    @Path("{identifiant}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(
            @PathParam("identifiant") String identifiant, Etudiant etudiant) {

        if (etB.updateEtudiant(identifiant, etudiant)) {
            return Response.status(200).build();
        } else {
            return Response.status(404).build();
        }
    }

    @GET
    @Path("option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsByOption(@QueryParam("codeOption") int codeOption) {

        Option option =
                optB.getOptionByCode(codeOption);

        if (option == null) {
            return Response.status(404).build();
        }

        List<Etudiant> l =
                etB.getEtudiantsByOption(option);

        return Response.status(200).entity(l).build();
    }
}