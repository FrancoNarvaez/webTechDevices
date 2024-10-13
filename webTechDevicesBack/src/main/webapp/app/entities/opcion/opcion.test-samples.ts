import { IOpcion, NewOpcion } from './opcion.model';

export const sampleWithRequiredData: IOpcion = {
  id: 6799,
  codigo: 'that furiously',
};

export const sampleWithPartialData: IOpcion = {
  id: 24087,
  codigo: 'duh an watery',
  nombre: 'whenever underneath incidentally',
  descripcion: 'forage what',
};

export const sampleWithFullData: IOpcion = {
  id: 12223,
  codigo: 'impassioned amidst',
  nombre: 'shinny lifestyle',
  descripcion: 'worriedly',
  precioAdicional: 32378.8,
};

export const sampleWithNewData: NewOpcion = {
  codigo: 'um whoa',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
