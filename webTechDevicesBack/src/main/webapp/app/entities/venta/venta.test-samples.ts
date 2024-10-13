import dayjs from 'dayjs/esm';

import { IVenta, NewVenta } from './venta.model';

export const sampleWithRequiredData: IVenta = {
  id: 16949,
  idVentaProfe: 6580,
  preciofinal: 8046.87,
};

export const sampleWithPartialData: IVenta = {
  id: 13426,
  idVentaProfe: 11103,
  descripcion: 'blah before',
  preciofinal: 24174.53,
  fecha: dayjs('2024-10-10T03:39'),
};

export const sampleWithFullData: IVenta = {
  id: 6789,
  idVentaProfe: 25822,
  descripcion: 'sneaky pace',
  preciofinal: 29954.15,
  fecha: dayjs('2024-10-10T13:03'),
};

export const sampleWithNewData: NewVenta = {
  idVentaProfe: 25966,
  preciofinal: 7870.95,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
