import { IDispositivo } from 'app/entities/dispositivo/dispositivo.model';

export interface IPersonalizacion {
  id: number;
  nombre?: string | null;
  descripcion?: string | null;
  opciones?: Pick<IDispositivo, 'id'> | null;
}

export type NewPersonalizacion = Omit<IPersonalizacion, 'id'> & { id: null };
