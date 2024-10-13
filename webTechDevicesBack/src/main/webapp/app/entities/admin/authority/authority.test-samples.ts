import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: '81013f19-82dc-49d8-8e3c-066780173a37',
};

export const sampleWithPartialData: IAuthority = {
  name: '7dc163fd-d877-48d4-84c8-51747c3ee741',
};

export const sampleWithFullData: IAuthority = {
  name: '89201440-d7b5-4201-bae0-23de9b084779',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
