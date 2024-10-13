import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'webTechDevicesBackApp.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'dispositivo',
    data: { pageTitle: 'webTechDevicesBackApp.dispositivo.home.title' },
    loadChildren: () => import('./dispositivo/dispositivo.routes'),
  },
  {
    path: 'caracteristica',
    data: { pageTitle: 'webTechDevicesBackApp.caracteristica.home.title' },
    loadChildren: () => import('./caracteristica/caracteristica.routes'),
  },
  {
    path: 'personalizacion',
    data: { pageTitle: 'webTechDevicesBackApp.personalizacion.home.title' },
    loadChildren: () => import('./personalizacion/personalizacion.routes'),
  },
  {
    path: 'opcion',
    data: { pageTitle: 'webTechDevicesBackApp.opcion.home.title' },
    loadChildren: () => import('./opcion/opcion.routes'),
  },
  {
    path: 'adicional',
    data: { pageTitle: 'webTechDevicesBackApp.adicional.home.title' },
    loadChildren: () => import('./adicional/adicional.routes'),
  },
  {
    path: 'venta',
    data: { pageTitle: 'webTechDevicesBackApp.venta.home.title' },
    loadChildren: () => import('./venta/venta.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
