import { IPersonalizacion, NewPersonalizacion } from './personalizacion.model';

export const sampleWithRequiredData: IPersonalizacion = {
  id: 4973,
  nombre: 'at gah neighboring',
};

export const sampleWithPartialData: IPersonalizacion = {
  id: 28537,
  nombre: 'reprimand gee',
};

export const sampleWithFullData: IPersonalizacion = {
  id: 9688,
  nombre: 'innovate hmph always',
  descripcion: 'via who separate',
};

export const sampleWithNewData: NewPersonalizacion = {
  nombre: 'puff inventory where',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
