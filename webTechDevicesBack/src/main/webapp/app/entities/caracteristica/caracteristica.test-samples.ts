import { ICaracteristica, NewCaracteristica } from './caracteristica.model';

export const sampleWithRequiredData: ICaracteristica = {
  id: 19441,
  nombre: 'that',
};

export const sampleWithPartialData: ICaracteristica = {
  id: 8647,
  nombre: 'aha where',
  descripcion: 'promptly whoa degenerate',
};

export const sampleWithFullData: ICaracteristica = {
  id: 11315,
  nombre: 'scramble',
  descripcion: 'napkin',
};

export const sampleWithNewData: NewCaracteristica = {
  nombre: 'nifty terribly uh-huh',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
