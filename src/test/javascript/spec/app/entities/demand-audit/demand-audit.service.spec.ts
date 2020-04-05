import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DemandAuditService } from 'app/entities/demand-audit/demand-audit.service';
import { IDemandAudit, DemandAudit } from 'app/shared/model/demand-audit.model';
import { DemandStatus } from 'app/shared/model/enumerations/demand-status.model';

describe('Service Tests', () => {
  describe('DemandAudit Service', () => {
    let injector: TestBed;
    let service: DemandAuditService;
    let httpMock: HttpTestingController;
    let elemDefault: IDemandAudit;
    let expectedResult: IDemandAudit | IDemandAudit[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DemandAuditService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DemandAudit(0, DemandStatus.OPEN, 'AAAAAAA', 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            creationDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of DemandAudit', () => {
        const returnedFromService = Object.assign(
          {
            status: 'BBBBBB',
            description: 'BBBBBB',
            ipAddress: 'BBBBBB',
            creationDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            creationDate: currentDate
          },
          returnedFromService
        );

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
