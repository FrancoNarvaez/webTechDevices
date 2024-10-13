import dayjs from 'dayjs/esm';

export interface IVenta {
  id: number;
  idVentaProfe?: number | null;
  descripcion?: string | null;
  preciofinal?: number | null;
  fecha?: dayjs.Dayjs | null;
}

export type NewVenta = Omit<IVenta, 'id'> & { id: null };
