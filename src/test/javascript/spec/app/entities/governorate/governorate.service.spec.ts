import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { GovernorateService } from 'app/entities/governorate/governorate.service';
import { IGovernorate, Governorate } from 'app/shared/model/governorate.model';

describe('Service Tests', () => {
  describe('Governorate Service', () => {
    let injector: TestBed;
    let service: GovernorateService;
    let httpMock: HttpTestingController;
    let elemDefault: IGovernorate;
    let expectedResult: IGovernorate | IGovernorate[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(GovernorateService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Governorate(0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of Governorate', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
