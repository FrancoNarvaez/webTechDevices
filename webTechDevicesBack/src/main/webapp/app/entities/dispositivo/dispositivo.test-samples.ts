import { IDispositivo, NewDispositivo } from './dispositivo.model';

export const sampleWithRequiredData: IDispositivo = {
  id: 29209,
  codigo: 'blah',
  nombre: 'haircut',
  precioBase: 14818.72,
  moneda: 'ew seldom',
};

export const sampleWithPartialData: IDispositivo = {
  id: 22828,
  codigo: 'scale uh-huh',
  nombre: 'an unnaturally beloved',
  descripcion: 'bah',
  precioBase: 12376.27,
  moneda: 'if heating innocently',
};

export const sampleWithFullData: IDispositivo = {
  id: 14668,
  codigo: 'gosh',
  nombre: 'even',
  descripcion: 'affect',
  precioBase: 4988,
  moneda: 'quietly',
};

export const sampleWithNewData: NewDispositivo = {
  codigo: 'the sweetly',
  nombre: 'moist',
  precioBase: 20914.06,
  moneda: 'near across against',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
