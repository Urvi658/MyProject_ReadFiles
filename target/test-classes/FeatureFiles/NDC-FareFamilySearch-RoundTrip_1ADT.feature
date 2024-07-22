@TestCase
Feature: NDC-FareFamilyPrice-RoundTrip_1ADT

@ScenarioCreation1G
  Scenario Outline: Pricing Comparision OneWay 1-ADT for Gen-1 and Gen-3 responses
    Given a countrycode <countrycode> with citycode <citycode> and iata <iata>
    And with dept flt from <deptfltfrom> to <deptfltto> with dept date <deptdays> days
    And with retn flight from <retnfltfrom> to <retnfltto> with retn date <retndays> days
    And with carrier <carrieropt> and idm_carrier_list <idm_carrier_list>
    And with currencycode <currencycode> and pseudocitycode <pseudocitycode> and domainregion <domainregion>
    And with oAuthResourceInfo '76F0ABA9-9516-4E4D-B3D2-3DAA918B8618' and Accessgroup '76F0ABA9-9516-4E4D-B3D2-3DAA918B8618'
    And with domainlistenerchannelid '11588c82-d48c-43e0-88c3-9b1dba2f7ad5' and environment 'Test'
    And with ReservationResource_Identifier '11588c82-d48c-43e0-88c3-9b1dba2f7ad5' and PCC '682J'
    And with 1 and 'ADT' passenger
    And with CoreAffinity as '1G' and upsells value '' and OffersToReturn '' 
    And Ignore below keywords or values while comparing Gen-1 and Gen-3 responses
    #| IgnoreDecimalZero |
    | BrandRef |
    | NextSteps|   
 	  When Invoke the Search request 'Search_FareFamily_OpenJawOrReturnTrip_1PTC' with endpoint 'NDC-Search' Request for 'GEN-1' Version
    #When Invoke the Search request 'Search_FareFamily_OneWay_2PTC' with endpoint 'NDC-Search' Request for 'GEN-1' Version
    
    #Then Remove tags from the response for below RequestType and Version
      #| TagName   | RequestType       | Version |
      #| NextSteps | Search_FareFamily | GEN-1   |
      #| Result    | Search_FareFamily | GEN-1   |
      #| authority |                   |         |
      #
    #Then Remove tags from the response for below RequestType and Version
     #| NextSteps | Search_FareFamily | GEN-3   |
      #| Result    | Search_FareFamily | GEN-3   |
      #| authority |                   |         |
    #
    #Then Remove tags by path from the response for below RequestType and Version      
      #| TagName                 | Search_FareFamily | GEN-3   |
      #| CatalogProductOfferingsResponse.CatalogProductOfferings.CatalogProductOffering[0].ProductBrandOptions[9].ProductBrandOffering[1].Identifier.authority |                   |         |
      #| CatalogProductOfferingsResponse.Result.Warning[0].Message |                   |         |
      
      
    #Then Validate and Compare the responses for "Search" both Versions
    #When Invoke the Price request 'Price' with endpoint 'NDC-PRICE' for 'GEN-1' Version
    #When Invoke the Price request 'Price' with endpoint 'NDC-PRICE' for 'GEN-3' Version
    #Then Validate and Compare the responses for "Price" for both Versions
    
      Examples: 
      | countrycode | citycode | iata     | deptfltfrom | deptfltto | deptdays | retnfltfrom | retnfltto | retndays | carrieropt | idm_carrier_list | currencycode | pseudocitycode | domainregion |
      | US          | LAX      | 9999999  | LAX         | DFW       |       30 | DFW         | LAX       |       40 | AA         | AA               | USD          | XB7            | zu2-dv       |
    
    