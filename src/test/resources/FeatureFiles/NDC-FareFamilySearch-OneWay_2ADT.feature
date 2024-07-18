@TestCase
Feature: NDC-FareFamilySearch-OneWay_2ADT

  @ScenarioCreation1G @SkipTest
  Scenario Outline: FareFamilySearch OneWay 1-ADT for Gen-2 and Gen-3 responses
    Given a countrycode <countrycode> with citycode <citycode> and iata <iata>
    And with dept flt from <deptfltfrom> to <deptfltto> with dept date <deptdays> days
    And with carrier <carrieropt> and idm_carrier_list <idm_carrier_list>
    And with currencycode <currencycode> and pseudocitycode <pseudocitycode> and domainregion <domainregion>
    And with domainlistenerchannelid 'domainlistenerchannelid' and environment 'Test'
    And with 1 and 'ADT' passenger
    #And Ignore below keywords or values while comparing Gen-1 and Gen-3 responses
    #| IgnoreDecimalZero |
    #| BrandRef |
    #| NextSteps|
    When Invoke the Search request 'Search_FareFamily_OneWay_2PTC' with endpoint 'NDC-Search' Request for 'GEN-1' Version
    #When Invoke the Price request 'Price_FareFamily' with endpoint 'NDC-PRICE' for 'GEN-1' Version
    #When Invoke the Search request 'Search_FareFamily_NextLeg' with endpoint 'NDC-Search-NextLeg' Request for 'GEN-1' Version
    When Invoke the Search request 'Search_FareFamily_OneWay_2PTC' with endpoint 'NDC-Search' Request for 'GEN-3' Version
    #When Execute the 'Search_FareFamily_NextLeg' with endpoint 'NDC-Search-NextLeg' Request for 'GEN-3' Version
    #Then Skip below tags while doing comparisions
      #| "@type": "CatalogProductOfferingsResponse", |
      
    #Then Remove tags from the response for below RequestType and Version
      #| TagName   | RequestType       | Version |
      #| NextSteps | Search_FareFamily | GEN-1   |
      #| Result    |                   |         |
    #Then Remove tags from the response for below RequestType and Version
      #| TagName   | RequestType       | Version |
      #| NextSteps | Search_FareFamily | GEN-1   |
      #| Result    |                   |         |
    #Then Validate and Compare the responses for "Search_FareFamily_OneWay_1PTC" both Versions

   
    Examples: 
      | countrycode | citycode | iata     | deptfltfrom | deptfltto | deptdays | carrieropt | idm_carrier_list | currencycode | pseudocitycode | domainregion |
      | SG          | BKK      | 32303843 | HKG         | BKK       |       30 | SQ         | SQ               | USD          | 795E           | zu2-dv       |
