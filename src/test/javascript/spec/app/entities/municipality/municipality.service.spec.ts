import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MunicipalityService } from 'app/entities/municipality/municipality.service';
import { IMunicipality, Municipality } from 'app/shared/model/municipality.model';

describe('Service Tests', () => {
  describe('Municipality Service', () => {
    let injector: TestBed;
    let service: MunicipalityService;
    let httpMock: HttpTestingController;
    let elemDefault: IMunicipality;
    let expectedResult: IMunicipality | IMunicipality[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MunicipalityService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Municipality(0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of Municipality', () => {
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
