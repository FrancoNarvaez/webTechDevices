import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 18958,
  login: '.s',
};

export const sampleWithPartialData: IUser = {
  id: 11282,
  login: "C*GMM@85\\'eBz-eq\\Z7F",
};

export const sampleWithFullData: IUser = {
  id: 28640,
  login: '4',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
