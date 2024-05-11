package utep.VandV;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class DealershipTest extends CollectionTestBase {




	@Override
	protected String getClassToCheck() {
		return "utep.VandV.Dealership";
	}

	public void test_DealershipConstructorTest() throws VizException {
		setConfigKeyRelevantClasses("utep.VandV.Dealership, utep.VandV.BrandSet, utep.VandV.SinglyLinkedListBrandNode, utep.VandV.Brand, utep.VandV.VendorSet, utep.VandV.SinglyLinkedListVendorNode, utep.VandV.Vendor, utep.VandV.CarSet, utep.VandV.SinglyLinkedListCarNode, utep.VandV.Car, utep.VandV.CarVendorMap, utep.VandV.SinglyLinkedListCarVendor");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyInferScope(true);
		setConfigKeyObjectScope(0);
		setConfigKeyIntBithwidth(7);
		setConfigKeyLoopUnroll(4);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(false);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(false);
		setConfigKeyMaxStrykerMethodsPerFile(1);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		setConfigKeyTypeScopes("utep.VandV.Dealership:2, utep.VandV.BrandSet:3, utep.VandV.SinglyLinkedListBrandNode:5, utep.VandV.Brand:5, utep.VandV.VendorSet:3, utep.VandV.SinglyLinkedListVendorNode:5, utep.VandV.Vendor:5, utep.VandV.CarSet:3, utep.VandV.SinglyLinkedListCarNode:5, utep.VandV.Car:5, utep.VandV.CarVendorMap:3, utep.VandV.SinglyLinkedListCarVendor:5");
		check(GENERIC_PROPERTIES,"Dealership(utep_VandV_BrandSet,utep_VandV_VendorSet, utep_VandV_CarSet)", false);
	}

	public void test_sellTest() throws VizException {
		setConfigKeyRelevantClasses("utep.VandV.Dealership, utep.VandV.BrandSet, utep.VandV.SinglyLinkedListBrandNode, utep.VandV.Brand, utep.VandV.VendorSet, utep.VandV.SinglyLinkedListVendorNode, utep.VandV.Vendor, utep.VandV.CarSet, utep.VandV.SinglyLinkedListCarNode, utep.VandV.Car, utep.VandV.CarVendorMap, utep.VandV.SinglyLinkedListCarVendor");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyInferScope(true);
		setConfigKeyObjectScope(0);
		setConfigKeyIntBithwidth(7);
		setConfigKeyLoopUnroll(4);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(false);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(false);
		setConfigKeyMaxStrykerMethodsPerFile(1);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		setConfigKeyTypeScopes("utep.VandV.Dealership:10, utep.VandV.BrandSet:10, utep.VandV.SinglyLinkedListBrandNode:10, utep.VandV.Brand:10, utep.VandV.VendorSet:10, utep.VandV.SinglyLinkedListVendorNode:10, utep.VandV.Vendor:10, utep.VandV.CarSet:10, utep.VandV.SinglyLinkedListCarNode:10, utep.VandV.Car:10, utep.VandV.CarVendorMap:10, utep.VandV.SinglyLinkedListCarVendor:10");
		check(GENERIC_PROPERTIES,"sell(utep_VandV_Car,utep_VandV_Vendor)", false);
	}


	public void test_buyTest() throws VizException {
		setConfigKeyRelevantClasses("utep.VandV.Dealership, utep.VandV.BrandSet, utep.VandV.SinglyLinkedListBrandNode, utep.VandV.Brand, utep.VandV.VendorSet, utep.VandV.SinglyLinkedListVendorNode, utep.VandV.Vendor, utep.VandV.CarSet, utep.VandV.SinglyLinkedListCarNode, utep.VandV.Car, utep.VandV.CarVendorMap, utep.VandV.SinglyLinkedListCarVendor");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyInferScope(true);
		setConfigKeyObjectScope(0);
		setConfigKeyIntBithwidth(7);
		setConfigKeyLoopUnroll(4);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(false);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(false);
		setConfigKeyMaxStrykerMethodsPerFile(1);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		setConfigKeyTypeScopes("utep.VandV.Dealership:10, utep.VandV.BrandSet:10, utep.VandV.SinglyLinkedListBrandNode:10, utep.VandV.Brand:10, utep.VandV.VendorSet:10, utep.VandV.SinglyLinkedListVendorNode:10, utep.VandV.Vendor:10, utep.VandV.CarSet:10, utep.VandV.SinglyLinkedListCarNode:10, utep.VandV.Car:10, utep.VandV.CarVendorMap:10, utep.VandV.SinglyLinkedListCarVendor:10");
		check(GENERIC_PROPERTIES,"buy(utep_VandV_Car)", false);
	}

}
