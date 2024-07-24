@TestCase
Feature: NDC-FareFamilyPrice-MultiCity_ADTCHDINF

  @ScenarioCreation1G
  Scenario Outline: Pricing Comparision MultiCity 2-ADT And 2-CHD And 2-INF for Gen-1 and Gen-3
    Given a countrycode <countrycode> with citycode <citycode> and iata <iata>
    And with dept flt from <deptfltfrom> to <deptfltto> with dept date <deptdays> days
    And with retn flight from <retnfltfrom> to <retnfltto> with retn date <retndays> days
    And with carrier <carrieropt> and idm_carrier_list <idm_carrier_list>
    And with currencycode <currencycode> and pseudocitycode <pseudocitycode> and domainregion <domainregion>
    And with oAuthResourceInfo '76F0ABA9-9516-4E4D-B3D2-3DAA918B8618' and Accessgroup '76F0ABA9-9516-4E4D-B3D2-3DAA918B8618'
    And with domainlistenerchannelid '11588c82-d48c-43e0-88c3-9b1dba2f7ad5' and environment 'Test'
    And with ReservationResource_Identifier '11588c82-d48c-43e0-88c3-9b1dba2f7ad5' and PCC '682J'
    And with 1 and 'ADT' passenger
    And with CoreAffinity as '1G' and upsells value '4' and OffersToReturn '50'      
    And with HCAProfileId 'ODTS_1G_680I_B58AEF' and BSPCode '016' and AgencyCountryCode '' 
    When Invoke the Search request 'Search_FareFamily_OpenJawOrReturnTrip' with endpoint 'NDC-Search' Request for 'GEN-1' Version
    When Invoke the Price request 'Price_MultiCity_AF' with endpoint 'NDC-PRICE' for 'GEN-1' Version
    When Invoke the Price request 'Price_MultiCity_AF' with endpoint 'NDC-PRICE' for 'GEN-3' Version
    Then Validate and Compare the responses for "Price" for both Versions

	 Examples: 
      | countrycode | citycode | iata     | deptfltfrom | deptfltto | deptdays | retnfltfrom | retnfltto | retndays | carrieropt | idm_carrier_list | currencycode | pseudocitycode | domainregion |
      | FR          | PAR      | 2026159  | DEL         | MEX       |       30 | MEX         | AMS       |       35 | AF         | AF               | EUR          | 680I           | zu2-dv       |
     

  