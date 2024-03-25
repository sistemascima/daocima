/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Anexo;
import entidades.Mantenimiento;
import entidades.Sede;
import entidades.Tranferencia;
import entidades.SUsuario;
import java.util.ArrayList;
import java.util.List;
import entidades.HojaDeVida;
import entidades.Objeto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Felipe Fontecha
 */
public class ObjetoJpaController implements Serializable {

    public ObjetoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Objeto objeto) throws PreexistingEntityException, Exception {
        if (objeto.getTranferenciaList() == null) {
            objeto.setTranferenciaList(new ArrayList<Tranferencia>());
        }
        if (objeto.getAnexoList() == null) {
            objeto.setAnexoList(new ArrayList<Anexo>());
        }
        if (objeto.getHojaDeVidaList() == null) {
            objeto.setHojaDeVidaList(new ArrayList<HojaDeVida>());
        }
        if (objeto.getMantenimientoList() == null) {
            objeto.setMantenimientoList(new ArrayList<Mantenimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Anexo IObjetoAnexoId = objeto.getIObjetoAnexoId();
            if (IObjetoAnexoId != null) {
                IObjetoAnexoId = em.getReference(IObjetoAnexoId.getClass(), IObjetoAnexoId.getPiAnexo());
                objeto.setIObjetoAnexoId(IObjetoAnexoId);
            }
            Mantenimiento IObjetoMantenimientoId = objeto.getIObjetoMantenimientoId();
            if (IObjetoMantenimientoId != null) {
                IObjetoMantenimientoId = em.getReference(IObjetoMantenimientoId.getClass(), IObjetoMantenimientoId.getPiMantenimiento());
                objeto.setIObjetoMantenimientoId(IObjetoMantenimientoId);
            }
            Sede IObjetoSedeId = objeto.getIObjetoSedeId();
            if (IObjetoSedeId != null) {
                IObjetoSedeId = em.getReference(IObjetoSedeId.getClass(), IObjetoSedeId.getPiSede());
                objeto.setIObjetoSedeId(IObjetoSedeId);
            }
            Tranferencia IObjetoTranferenciaId = objeto.getIObjetoTranferenciaId();
            if (IObjetoTranferenciaId != null) {
                IObjetoTranferenciaId = em.getReference(IObjetoTranferenciaId.getClass(), IObjetoTranferenciaId.getPiTranferencia());
                objeto.setIObjetoTranferenciaId(IObjetoTranferenciaId);
            }
            /*SUsuario SObjetoUsuarioId = objeto.getSObjetoUsuarioId();
            if (SObjetoUsuarioId != null) {
                SObjetoUsuarioId = em.getReference(SObjetoUsuarioId.getClass(), SObjetoUsuarioId.getUsuarioCod());
                objeto.setSObjetoUsuarioId(SObjetoUsuarioId);
            }*/
            List<Tranferencia> attachedTranferenciaList = new ArrayList<Tranferencia>();
            for (Tranferencia tranferenciaListTranferenciaToAttach : objeto.getTranferenciaList()) {
                tranferenciaListTranferenciaToAttach = em.getReference(tranferenciaListTranferenciaToAttach.getClass(), tranferenciaListTranferenciaToAttach.getPiTranferencia());
                attachedTranferenciaList.add(tranferenciaListTranferenciaToAttach);
            }
            objeto.setTranferenciaList(attachedTranferenciaList);
            List<Anexo> attachedAnexoList = new ArrayList<Anexo>();
            for (Anexo anexoListAnexoToAttach : objeto.getAnexoList()) {
                anexoListAnexoToAttach = em.getReference(anexoListAnexoToAttach.getClass(), anexoListAnexoToAttach.getPiAnexo());
                attachedAnexoList.add(anexoListAnexoToAttach);
            }
            objeto.setAnexoList(attachedAnexoList);
            List<HojaDeVida> attachedHojaDeVidaList = new ArrayList<HojaDeVida>();
            for (HojaDeVida hojaDeVidaListHojaDeVidaToAttach : objeto.getHojaDeVidaList()) {
                hojaDeVidaListHojaDeVidaToAttach = em.getReference(hojaDeVidaListHojaDeVidaToAttach.getClass(), hojaDeVidaListHojaDeVidaToAttach.getHojaDeVidaPK());
                attachedHojaDeVidaList.add(hojaDeVidaListHojaDeVidaToAttach);
            }
            objeto.setHojaDeVidaList(attachedHojaDeVidaList);
            List<Mantenimiento> attachedMantenimientoList = new ArrayList<Mantenimiento>();
            for (Mantenimiento mantenimientoListMantenimientoToAttach : objeto.getMantenimientoList()) {
                mantenimientoListMantenimientoToAttach = em.getReference(mantenimientoListMantenimientoToAttach.getClass(), mantenimientoListMantenimientoToAttach.getPiMantenimiento());
                attachedMantenimientoList.add(mantenimientoListMantenimientoToAttach);
            }
            objeto.setMantenimientoList(attachedMantenimientoList);
            em.persist(objeto);
            if (IObjetoAnexoId != null) {
                Objeto oldSAnexoObjetoIdOfIObjetoAnexoId = IObjetoAnexoId.getSAnexoObjetoId();
                if (oldSAnexoObjetoIdOfIObjetoAnexoId != null) {
                    oldSAnexoObjetoIdOfIObjetoAnexoId.setIObjetoAnexoId(null);
                    oldSAnexoObjetoIdOfIObjetoAnexoId = em.merge(oldSAnexoObjetoIdOfIObjetoAnexoId);
                }
                IObjetoAnexoId.setSAnexoObjetoId(objeto);
                IObjetoAnexoId = em.merge(IObjetoAnexoId);
            }
            if (IObjetoMantenimientoId != null) {
                IObjetoMantenimientoId.getObjetoList().add(objeto);
                IObjetoMantenimientoId = em.merge(IObjetoMantenimientoId);
            }
            if (IObjetoSedeId != null) {
                IObjetoSedeId.getObjetoList().add(objeto);
                IObjetoSedeId = em.merge(IObjetoSedeId);
            }
            if (IObjetoTranferenciaId != null) {
                Objeto oldSTranferenciaObjetoIdOfIObjetoTranferenciaId = IObjetoTranferenciaId.getSTranferenciaObjetoId();
                if (oldSTranferenciaObjetoIdOfIObjetoTranferenciaId != null) {
                    oldSTranferenciaObjetoIdOfIObjetoTranferenciaId.setIObjetoTranferenciaId(null);
                    oldSTranferenciaObjetoIdOfIObjetoTranferenciaId = em.merge(oldSTranferenciaObjetoIdOfIObjetoTranferenciaId);
                }
                IObjetoTranferenciaId.setSTranferenciaObjetoId(objeto);
                IObjetoTranferenciaId = em.merge(IObjetoTranferenciaId);
            }
          /*  if (SObjetoUsuarioId != null) {
                SObjetoUsuarioId.getObjetoList().add(objeto);
                SObjetoUsuarioId = em.merge(SObjetoUsuarioId);
            }*/
            for (Tranferencia tranferenciaListTranferencia : objeto.getTranferenciaList()) {
                Objeto oldSTranferenciaObjetoIdOfTranferenciaListTranferencia = tranferenciaListTranferencia.getSTranferenciaObjetoId();
                tranferenciaListTranferencia.setSTranferenciaObjetoId(objeto);
                tranferenciaListTranferencia = em.merge(tranferenciaListTranferencia);
                if (oldSTranferenciaObjetoIdOfTranferenciaListTranferencia != null) {
                    oldSTranferenciaObjetoIdOfTranferenciaListTranferencia.getTranferenciaList().remove(tranferenciaListTranferencia);
                    oldSTranferenciaObjetoIdOfTranferenciaListTranferencia = em.merge(oldSTranferenciaObjetoIdOfTranferenciaListTranferencia);
                }
            }
            for (Anexo anexoListAnexo : objeto.getAnexoList()) {
                Objeto oldSAnexoObjetoIdOfAnexoListAnexo = anexoListAnexo.getSAnexoObjetoId();
                anexoListAnexo.setSAnexoObjetoId(objeto);
                anexoListAnexo = em.merge(anexoListAnexo);
                if (oldSAnexoObjetoIdOfAnexoListAnexo != null) {
                    oldSAnexoObjetoIdOfAnexoListAnexo.getAnexoList().remove(anexoListAnexo);
                    oldSAnexoObjetoIdOfAnexoListAnexo = em.merge(oldSAnexoObjetoIdOfAnexoListAnexo);
                }
            }
            for (HojaDeVida hojaDeVidaListHojaDeVida : objeto.getHojaDeVidaList()) {
                Objeto oldSHojaDeVidaObjetoIdOfHojaDeVidaListHojaDeVida = hojaDeVidaListHojaDeVida.getSHojaDeVidaObjetoId();
                hojaDeVidaListHojaDeVida.setSHojaDeVidaObjetoId(objeto);
                hojaDeVidaListHojaDeVida = em.merge(hojaDeVidaListHojaDeVida);
                if (oldSHojaDeVidaObjetoIdOfHojaDeVidaListHojaDeVida != null) {
                    oldSHojaDeVidaObjetoIdOfHojaDeVidaListHojaDeVida.getHojaDeVidaList().remove(hojaDeVidaListHojaDeVida);
                    oldSHojaDeVidaObjetoIdOfHojaDeVidaListHojaDeVida = em.merge(oldSHojaDeVidaObjetoIdOfHojaDeVidaListHojaDeVida);
                }
            }
            for (Mantenimiento mantenimientoListMantenimiento : objeto.getMantenimientoList()) {
                Objeto oldSMantenimientoObjetoIdOfMantenimientoListMantenimiento = mantenimientoListMantenimiento.getSMantenimientoObjetoId();
                mantenimientoListMantenimiento.setSMantenimientoObjetoId(objeto);
                mantenimientoListMantenimiento = em.merge(mantenimientoListMantenimiento);
                if (oldSMantenimientoObjetoIdOfMantenimientoListMantenimiento != null) {
                    oldSMantenimientoObjetoIdOfMantenimientoListMantenimiento.getMantenimientoList().remove(mantenimientoListMantenimiento);
                    oldSMantenimientoObjetoIdOfMantenimientoListMantenimiento = em.merge(oldSMantenimientoObjetoIdOfMantenimientoListMantenimiento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjeto(objeto.getPiObjeto()) != null) {
                throw new PreexistingEntityException("Objeto " + objeto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /*public void edit(Objeto objeto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objeto persistentObjeto = em.find(Objeto.class, objeto.getPiObjeto());
            Anexo IObjetoAnexoIdOld = persistentObjeto.getIObjetoAnexoId();
            Anexo IObjetoAnexoIdNew = objeto.getIObjetoAnexoId();
            Mantenimiento IObjetoMantenimientoIdOld = persistentObjeto.getIObjetoMantenimientoId();
            Mantenimiento IObjetoMantenimientoIdNew = objeto.getIObjetoMantenimientoId();
            Sede IObjetoSedeIdOld = persistentObjeto.getIObjetoSedeId();
            Sede IObjetoSedeIdNew = objeto.getIObjetoSedeId();
            Tranferencia IObjetoTranferenciaIdOld = persistentObjeto.getIObjetoTranferenciaId();
            Tranferencia IObjetoTranferenciaIdNew = objeto.getIObjetoTranferenciaId();
            SUsuario SObjetoUsuarioIdOld = persistentObjeto.getSObjetoUsuarioId();
            SUsuario SObjetoUsuarioIdNew = objeto.getSObjetoUsuarioId();
            List<Tranferencia> tranferenciaListOld = persistentObjeto.getTranferenciaList();
            List<Tranferencia> tranferenciaListNew = objeto.getTranferenciaList();
            List<Anexo> anexoListOld = persistentObjeto.getAnexoList();
            List<Anexo> anexoListNew = objeto.getAnexoList();
            List<HojaDeVida> hojaDeVidaListOld = persistentObjeto.getHojaDeVidaList();
            List<HojaDeVida> hojaDeVidaListNew = objeto.getHojaDeVidaList();
            List<Mantenimiento> mantenimientoListOld = persistentObjeto.getMantenimientoList();
            List<Mantenimiento> mantenimientoListNew = objeto.getMantenimientoList();
            List<String> illegalOrphanMessages = null;
            if (IObjetoAnexoIdOld != null && !IObjetoAnexoIdOld.equals(IObjetoAnexoIdNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Anexo " + IObjetoAnexoIdOld + " since its SAnexoObjetoId field is not nullable.");
            }
            if (IObjetoTranferenciaIdOld != null && !IObjetoTranferenciaIdOld.equals(IObjetoTranferenciaIdNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Tranferencia " + IObjetoTranferenciaIdOld + " since its STranferenciaObjetoId field is not nullable.");
            }
            for (Tranferencia tranferenciaListOldTranferencia : tranferenciaListOld) {
                if (!tranferenciaListNew.contains(tranferenciaListOldTranferencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tranferencia " + tranferenciaListOldTranferencia + " since its STranferenciaObjetoId field is not nullable.");
                }
            }
            for (Anexo anexoListOldAnexo : anexoListOld) {
                if (!anexoListNew.contains(anexoListOldAnexo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Anexo " + anexoListOldAnexo + " since its SAnexoObjetoId field is not nullable.");
                }
            }
            for (HojaDeVida hojaDeVidaListOldHojaDeVida : hojaDeVidaListOld) {
                if (!hojaDeVidaListNew.contains(hojaDeVidaListOldHojaDeVida)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HojaDeVida " + hojaDeVidaListOldHojaDeVida + " since its SHojaDeVidaObjetoId field is not nullable.");
                }
            }
            for (Mantenimiento mantenimientoListOldMantenimiento : mantenimientoListOld) {
                if (!mantenimientoListNew.contains(mantenimientoListOldMantenimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mantenimiento " + mantenimientoListOldMantenimiento + " since its SMantenimientoObjetoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (IObjetoAnexoIdNew != null) {
                IObjetoAnexoIdNew = em.getReference(IObjetoAnexoIdNew.getClass(), IObjetoAnexoIdNew.getPiAnexo());
                objeto.setIObjetoAnexoId(IObjetoAnexoIdNew);
            }
            if (IObjetoMantenimientoIdNew != null) {
                IObjetoMantenimientoIdNew = em.getReference(IObjetoMantenimientoIdNew.getClass(), IObjetoMantenimientoIdNew.getPiMantenimiento());
                objeto.setIObjetoMantenimientoId(IObjetoMantenimientoIdNew);
            }
            if (IObjetoSedeIdNew != null) {
                IObjetoSedeIdNew = em.getReference(IObjetoSedeIdNew.getClass(), IObjetoSedeIdNew.getPiSede());
                objeto.setIObjetoSedeId(IObjetoSedeIdNew);
            }
            if (IObjetoTranferenciaIdNew != null) {
                IObjetoTranferenciaIdNew = em.getReference(IObjetoTranferenciaIdNew.getClass(), IObjetoTranferenciaIdNew.getPiTranferencia());
                objeto.setIObjetoTranferenciaId(IObjetoTranferenciaIdNew);
            }
            /*if (SObjetoUsuarioIdNew != null) {
                SObjetoUsuarioIdNew = em.getReference(SObjetoUsuarioIdNew.getClass(), SObjetoUsuarioIdNew.getUsuarioCod());
                objeto.setSObjetoUsuarioId(SObjetoUsuarioIdNew);
            }
            List<Tranferencia> attachedTranferenciaListNew = new ArrayList<Tranferencia>();
            for (Tranferencia tranferenciaListNewTranferenciaToAttach : tranferenciaListNew) {
                tranferenciaListNewTranferenciaToAttach = em.getReference(tranferenciaListNewTranferenciaToAttach.getClass(), tranferenciaListNewTranferenciaToAttach.getPiTranferencia());
                attachedTranferenciaListNew.add(tranferenciaListNewTranferenciaToAttach);
            }
            tranferenciaListNew = attachedTranferenciaListNew;
            objeto.setTranferenciaList(tranferenciaListNew);
            List<Anexo> attachedAnexoListNew = new ArrayList<Anexo>();
            for (Anexo anexoListNewAnexoToAttach : anexoListNew) {
                anexoListNewAnexoToAttach = em.getReference(anexoListNewAnexoToAttach.getClass(), anexoListNewAnexoToAttach.getPiAnexo());
                attachedAnexoListNew.add(anexoListNewAnexoToAttach);
            }
            anexoListNew = attachedAnexoListNew;
            objeto.setAnexoList(anexoListNew);
            List<HojaDeVida> attachedHojaDeVidaListNew = new ArrayList<HojaDeVida>();
            for (HojaDeVida hojaDeVidaListNewHojaDeVidaToAttach : hojaDeVidaListNew) {
                hojaDeVidaListNewHojaDeVidaToAttach = em.getReference(hojaDeVidaListNewHojaDeVidaToAttach.getClass(), hojaDeVidaListNewHojaDeVidaToAttach.getHojaDeVidaPK());
                attachedHojaDeVidaListNew.add(hojaDeVidaListNewHojaDeVidaToAttach);
            }
            hojaDeVidaListNew = attachedHojaDeVidaListNew;
            objeto.setHojaDeVidaList(hojaDeVidaListNew);
            List<Mantenimiento> attachedMantenimientoListNew = new ArrayList<Mantenimiento>();
            for (Mantenimiento mantenimientoListNewMantenimientoToAttach : mantenimientoListNew) {
                mantenimientoListNewMantenimientoToAttach = em.getReference(mantenimientoListNewMantenimientoToAttach.getClass(), mantenimientoListNewMantenimientoToAttach.getPiMantenimiento());
                attachedMantenimientoListNew.add(mantenimientoListNewMantenimientoToAttach);
            }
            mantenimientoListNew = attachedMantenimientoListNew;
            objeto.setMantenimientoList(mantenimientoListNew);
            objeto = em.merge(objeto);
            if (IObjetoAnexoIdNew != null && !IObjetoAnexoIdNew.equals(IObjetoAnexoIdOld)) {
                Objeto oldSAnexoObjetoIdOfIObjetoAnexoId = IObjetoAnexoIdNew.getSAnexoObjetoId();
                if (oldSAnexoObjetoIdOfIObjetoAnexoId != null) {
                    oldSAnexoObjetoIdOfIObjetoAnexoId.setIObjetoAnexoId(null);
                    oldSAnexoObjetoIdOfIObjetoAnexoId = em.merge(oldSAnexoObjetoIdOfIObjetoAnexoId);
                }
                IObjetoAnexoIdNew.setSAnexoObjetoId(objeto);
                IObjetoAnexoIdNew = em.merge(IObjetoAnexoIdNew);
            }
            if (IObjetoMantenimientoIdOld != null && !IObjetoMantenimientoIdOld.equals(IObjetoMantenimientoIdNew)) {
                IObjetoMantenimientoIdOld.getObjetoList().remove(objeto);
                IObjetoMantenimientoIdOld = em.merge(IObjetoMantenimientoIdOld);
            }
            if (IObjetoMantenimientoIdNew != null && !IObjetoMantenimientoIdNew.equals(IObjetoMantenimientoIdOld)) {
                IObjetoMantenimientoIdNew.getObjetoList().add(objeto);
                IObjetoMantenimientoIdNew = em.merge(IObjetoMantenimientoIdNew);
            }
            if (IObjetoSedeIdOld != null && !IObjetoSedeIdOld.equals(IObjetoSedeIdNew)) {
                IObjetoSedeIdOld.getObjetoList().remove(objeto);
                IObjetoSedeIdOld = em.merge(IObjetoSedeIdOld);
            }
            if (IObjetoSedeIdNew != null && !IObjetoSedeIdNew.equals(IObjetoSedeIdOld)) {
                IObjetoSedeIdNew.getObjetoList().add(objeto);
                IObjetoSedeIdNew = em.merge(IObjetoSedeIdNew);
            }
            if (IObjetoTranferenciaIdNew != null && !IObjetoTranferenciaIdNew.equals(IObjetoTranferenciaIdOld)) {
                Objeto oldSTranferenciaObjetoIdOfIObjetoTranferenciaId = IObjetoTranferenciaIdNew.getSTranferenciaObjetoId();
                if (oldSTranferenciaObjetoIdOfIObjetoTranferenciaId != null) {
                    oldSTranferenciaObjetoIdOfIObjetoTranferenciaId.setIObjetoTranferenciaId(null);
                    oldSTranferenciaObjetoIdOfIObjetoTranferenciaId = em.merge(oldSTranferenciaObjetoIdOfIObjetoTranferenciaId);
                }
                IObjetoTranferenciaIdNew.setSTranferenciaObjetoId(objeto);
                IObjetoTranferenciaIdNew = em.merge(IObjetoTranferenciaIdNew);
            }
           /* if (SObjetoUsuarioIdOld != null && !SObjetoUsuarioIdOld.equals(SObjetoUsuarioIdNew)) {
                SObjetoUsuarioIdOld.getObjetoList().remove(objeto);
                SObjetoUsuarioIdOld = em.merge(SObjetoUsuarioIdOld);
            }
            if (SObjetoUsuarioIdNew != null && !SObjetoUsuarioIdNew.equals(SObjetoUsuarioIdOld)) {
                SObjetoUsuarioIdNew.getObjetoList().add(objeto);
                SObjetoUsuarioIdNew = em.merge(SObjetoUsuarioIdNew);
            }
            for (Tranferencia tranferenciaListNewTranferencia : tranferenciaListNew) {
                if (!tranferenciaListOld.contains(tranferenciaListNewTranferencia)) {
                    Objeto oldSTranferenciaObjetoIdOfTranferenciaListNewTranferencia = tranferenciaListNewTranferencia.getSTranferenciaObjetoId();
                    tranferenciaListNewTranferencia.setSTranferenciaObjetoId(objeto);
                    tranferenciaListNewTranferencia = em.merge(tranferenciaListNewTranferencia);
                    if (oldSTranferenciaObjetoIdOfTranferenciaListNewTranferencia != null && !oldSTranferenciaObjetoIdOfTranferenciaListNewTranferencia.equals(objeto)) {
                        oldSTranferenciaObjetoIdOfTranferenciaListNewTranferencia.getTranferenciaList().remove(tranferenciaListNewTranferencia);
                        oldSTranferenciaObjetoIdOfTranferenciaListNewTranferencia = em.merge(oldSTranferenciaObjetoIdOfTranferenciaListNewTranferencia);
                    }
                }
            }
            for (Anexo anexoListNewAnexo : anexoListNew) {
                if (!anexoListOld.contains(anexoListNewAnexo)) {
                    Objeto oldSAnexoObjetoIdOfAnexoListNewAnexo = anexoListNewAnexo.getSAnexoObjetoId();
                    anexoListNewAnexo.setSAnexoObjetoId(objeto);
                    anexoListNewAnexo = em.merge(anexoListNewAnexo);
                    if (oldSAnexoObjetoIdOfAnexoListNewAnexo != null && !oldSAnexoObjetoIdOfAnexoListNewAnexo.equals(objeto)) {
                        oldSAnexoObjetoIdOfAnexoListNewAnexo.getAnexoList().remove(anexoListNewAnexo);
                        oldSAnexoObjetoIdOfAnexoListNewAnexo = em.merge(oldSAnexoObjetoIdOfAnexoListNewAnexo);
                    }
                }
            }
            for (HojaDeVida hojaDeVidaListNewHojaDeVida : hojaDeVidaListNew) {
                if (!hojaDeVidaListOld.contains(hojaDeVidaListNewHojaDeVida)) {
                    Objeto oldSHojaDeVidaObjetoIdOfHojaDeVidaListNewHojaDeVida = hojaDeVidaListNewHojaDeVida.getSHojaDeVidaObjetoId();
                    hojaDeVidaListNewHojaDeVida.setSHojaDeVidaObjetoId(objeto);
                    hojaDeVidaListNewHojaDeVida = em.merge(hojaDeVidaListNewHojaDeVida);
                    if (oldSHojaDeVidaObjetoIdOfHojaDeVidaListNewHojaDeVida != null && !oldSHojaDeVidaObjetoIdOfHojaDeVidaListNewHojaDeVida.equals(objeto)) {
                        oldSHojaDeVidaObjetoIdOfHojaDeVidaListNewHojaDeVida.getHojaDeVidaList().remove(hojaDeVidaListNewHojaDeVida);
                        oldSHojaDeVidaObjetoIdOfHojaDeVidaListNewHojaDeVida = em.merge(oldSHojaDeVidaObjetoIdOfHojaDeVidaListNewHojaDeVida);
                    }
                }
            }
            for (Mantenimiento mantenimientoListNewMantenimiento : mantenimientoListNew) {
                if (!mantenimientoListOld.contains(mantenimientoListNewMantenimiento)) {
                    Objeto oldSMantenimientoObjetoIdOfMantenimientoListNewMantenimiento = mantenimientoListNewMantenimiento.getSMantenimientoObjetoId();
                    mantenimientoListNewMantenimiento.setSMantenimientoObjetoId(objeto);
                    mantenimientoListNewMantenimiento = em.merge(mantenimientoListNewMantenimiento);
                    if (oldSMantenimientoObjetoIdOfMantenimientoListNewMantenimiento != null && !oldSMantenimientoObjetoIdOfMantenimientoListNewMantenimiento.equals(objeto)) {
                        oldSMantenimientoObjetoIdOfMantenimientoListNewMantenimiento.getMantenimientoList().remove(mantenimientoListNewMantenimiento);
                        oldSMantenimientoObjetoIdOfMantenimientoListNewMantenimiento = em.merge(oldSMantenimientoObjetoIdOfMantenimientoListNewMantenimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = objeto.getPiObjeto();
                if (findObjeto(id) == null) {
                    throw new NonexistentEntityException("The objeto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    /*public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objeto objeto;
            try {
                objeto = em.getReference(Objeto.class, id);
                objeto.getPiObjeto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objeto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Anexo IObjetoAnexoIdOrphanCheck = objeto.getIObjetoAnexoId();
            if (IObjetoAnexoIdOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objeto (" + objeto + ") cannot be destroyed since the Anexo " + IObjetoAnexoIdOrphanCheck + " in its IObjetoAnexoId field has a non-nullable SAnexoObjetoId field.");
            }
            Tranferencia IObjetoTranferenciaIdOrphanCheck = objeto.getIObjetoTranferenciaId();
            if (IObjetoTranferenciaIdOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objeto (" + objeto + ") cannot be destroyed since the Tranferencia " + IObjetoTranferenciaIdOrphanCheck + " in its IObjetoTranferenciaId field has a non-nullable STranferenciaObjetoId field.");
            }
            List<Tranferencia> tranferenciaListOrphanCheck = objeto.getTranferenciaList();
            for (Tranferencia tranferenciaListOrphanCheckTranferencia : tranferenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objeto (" + objeto + ") cannot be destroyed since the Tranferencia " + tranferenciaListOrphanCheckTranferencia + " in its tranferenciaList field has a non-nullable STranferenciaObjetoId field.");
            }
            List<Anexo> anexoListOrphanCheck = objeto.getAnexoList();
            for (Anexo anexoListOrphanCheckAnexo : anexoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objeto (" + objeto + ") cannot be destroyed since the Anexo " + anexoListOrphanCheckAnexo + " in its anexoList field has a non-nullable SAnexoObjetoId field.");
            }
            List<HojaDeVida> hojaDeVidaListOrphanCheck = objeto.getHojaDeVidaList();
            for (HojaDeVida hojaDeVidaListOrphanCheckHojaDeVida : hojaDeVidaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objeto (" + objeto + ") cannot be destroyed since the HojaDeVida " + hojaDeVidaListOrphanCheckHojaDeVida + " in its hojaDeVidaList field has a non-nullable SHojaDeVidaObjetoId field.");
            }
            List<Mantenimiento> mantenimientoListOrphanCheck = objeto.getMantenimientoList();
            for (Mantenimiento mantenimientoListOrphanCheckMantenimiento : mantenimientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objeto (" + objeto + ") cannot be destroyed since the Mantenimiento " + mantenimientoListOrphanCheckMantenimiento + " in its mantenimientoList field has a non-nullable SMantenimientoObjetoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Mantenimiento IObjetoMantenimientoId = objeto.getIObjetoMantenimientoId();
            if (IObjetoMantenimientoId != null) {
                IObjetoMantenimientoId.getObjetoList().remove(objeto);
                IObjetoMantenimientoId = em.merge(IObjetoMantenimientoId);
            }
            Sede IObjetoSedeId = objeto.getIObjetoSedeId();
            if (IObjetoSedeId != null) {
                IObjetoSedeId.getObjetoList().remove(objeto);
                IObjetoSedeId = em.merge(IObjetoSedeId);
            }
            SUsuario SObjetoUsuarioId = objeto.getSObjetoUsuarioId();
            /*if (SObjetoUsuarioId != null) {
                SObjetoUsuarioId.getObjetoList().remove(objeto);
                SObjetoUsuarioId = em.merge(SObjetoUsuarioId);
            }
            em.remove(objeto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<Objeto> findObjetoEntities() {
        return findObjetoEntities(true, -1, -1);
    }

    public List<Objeto> findObjetoEntities(int maxResults, int firstResult) {
        return findObjetoEntities(false, maxResults, firstResult);
    }

    private List<Objeto> findObjetoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Objeto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Objeto findObjeto(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objeto.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Objeto> rt = cq.from(Objeto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
