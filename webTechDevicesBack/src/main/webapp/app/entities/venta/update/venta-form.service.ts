import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IVenta, NewVenta } from '../venta.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVenta for edit and NewVentaFormGroupInput for create.
 */
type VentaFormGroupInput = IVenta | PartialWithRequiredKeyOf<NewVenta>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IVenta | NewVenta> = Omit<T, 'fecha'> & {
  fecha?: string | null;
};

type VentaFormRawValue = FormValueOf<IVenta>;

type NewVentaFormRawValue = FormValueOf<NewVenta>;

type VentaFormDefaults = Pick<NewVenta, 'id' | 'fecha'>;

type VentaFormGroupContent = {
  id: FormControl<VentaFormRawValue['id'] | NewVenta['id']>;
  idVentaProfe: FormControl<VentaFormRawValue['idVentaProfe']>;
  descripcion: FormControl<VentaFormRawValue['descripcion']>;
  preciofinal: FormControl<VentaFormRawValue['preciofinal']>;
  fecha: FormControl<VentaFormRawValue['fecha']>;
};

export type VentaFormGroup = FormGroup<VentaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VentaFormService {
  createVentaFormGroup(venta: VentaFormGroupInput = { id: null }): VentaFormGroup {
    const ventaRawValue = this.convertVentaToVentaRawValue({
      ...this.getFormDefaults(),
      ...venta,
    });
    return new FormGroup<VentaFormGroupContent>({
      id: new FormControl(
        { value: ventaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idVentaProfe: new FormControl(ventaRawValue.idVentaProfe, {
        validators: [Validators.required],
      }),
      descripcion: new FormControl(ventaRawValue.descripcion),
      preciofinal: new FormControl(ventaRawValue.preciofinal, {
        validators: [Validators.required],
      }),
      fecha: new FormControl(ventaRawValue.fecha),
    });
  }

  getVenta(form: VentaFormGroup): IVenta | NewVenta {
    return this.convertVentaRawValueToVenta(form.getRawValue() as VentaFormRawValue | NewVentaFormRawValue);
  }

  resetForm(form: VentaFormGroup, venta: VentaFormGroupInput): void {
    const ventaRawValue = this.convertVentaToVentaRawValue({ ...this.getFormDefaults(), ...venta });
    form.reset(
      {
        ...ventaRawValue,
        id: { value: ventaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): VentaFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecha: currentTime,
    };
  }

  private convertVentaRawValueToVenta(rawVenta: VentaFormRawValue | NewVentaFormRawValue): IVenta | NewVenta {
    return {
      ...rawVenta,
      fecha: dayjs(rawVenta.fecha, DATE_TIME_FORMAT),
    };
  }

  private convertVentaToVentaRawValue(
    venta: IVenta | (Partial<NewVenta> & VentaFormDefaults),
  ): VentaFormRawValue | PartialWithRequiredKeyOf<NewVentaFormRawValue> {
    return {
      ...venta,
      fecha: venta.fecha ? venta.fecha.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
