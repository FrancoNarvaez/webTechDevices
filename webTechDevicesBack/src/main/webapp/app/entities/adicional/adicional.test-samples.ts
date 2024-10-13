import { IAdicional, NewAdicional } from './adicional.model';

export const sampleWithRequiredData: IAdicional = {
  id: 13270,
  nombre: 'supposing psst focused',
  precio: 30757.8,
  precioGratis: 25790.05,
};

export const sampleWithPartialData: IAdicional = {
  id: 1050,
  nombre: 'obedience instead gadzooks',
  descripcion: 'around when',
  precio: 1106.46,
  precioGratis: 31159,
};

export const sampleWithFullData: IAdicional = {
  id: 3770,
  nombre: 'er since',
  descripcion: 'yahoo worth',
  precio: 10698.61,
  precioGratis: 27205.76,
};

export const sampleWithNewData: NewAdicional = {
  nombre: 'yuck ack',
  precio: 29866.28,
  precioGratis: 22287.95,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
